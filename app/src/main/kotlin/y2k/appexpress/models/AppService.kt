package y2k.appexpress.models

import rx.Observable

/**
 * Created by y2k on 1/1/16.
 */
class AppService(
    private val packageService: PackageService,
    private val storageService: StorageService) {

    fun getApps(): Observable<List<App>> {
        var regex = Regex("([\\w\\d\\.]+)-([\\d\\.]*\\d+)")
        return storageService
            .list()
            .flatMap { Observable.from(it) }
            .flatMap { storageService.list(it) }
            .filter { !it.isEmpty() }
            .map { files ->
                files
                    .map { regex.find(it.name)?.groups }
                    .map { info -> info?.let { App(files[0].parentFile.name, it[1]!!.value, it[2]!!.value) } }
                    .filter { it != null }
                    .maxBy { it!!.serverVersion }
            }
            .filter { it != null }.map { it!! }
            .toList()
            .observeOn(UIScheduler.scheduler)
    }
}
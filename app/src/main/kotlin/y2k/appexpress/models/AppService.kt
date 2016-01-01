package y2k.appexpress.models

import rx.Observable

//
// Created by y2k on 1/1/16.
//
class AppService(
    private val packageService: PackageService,
    private val storageService: CloudStorageService) {

    fun getApps(): Observable<List<App>> {
        return storageService.list()
            .flatMap { Observable.from(it) }
            .flatMap { storageService.list(it) }
            .filter { !it.isEmpty() }
            .map { files ->
                files
                    .map { infoRegex.find(it.name)?.groups }
                    .map { info -> info?.let { App(files[0].parentFile.name, it[1]!!.value, Version(it[2]!!.value)) } }
                    .filter { it != null }
                    .maxBy { it!!.serverVersion }
            }
            .filter { it != null }.map { it!! }
            .doOnNext { it.installedVersion = packageService.getVersion(it.packageName) }
            .toList()
            .map { it.sortedBy { it.title } }
            .observeOn(UIScheduler.scheduler)
    }

    companion object {

        val infoRegex = Regex("([\\w\\d\\.]+)-([\\d\\.]*\\d+)")
    }
}
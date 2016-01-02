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
                    .map { info ->
                        info?.let {
                            val packageName = it[1]!!.value
                            val title = files[0].parentFile.name
                            val version = Version(it[2]!!.value)
                            App(title, packageName, version, packageService.getVersion(packageName))
                        }
                    }
                    .filter { it != null }
                    .maxBy { it!!.serverVersion }
            }
            .filter { it != null }.map { it!! }
            .toList()
            .map { it.sortedBy { it.title } }
            .observeOn(UIScheduler.scheduler)
    }

    fun installApp(app: App) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        private val infoRegex = Regex("([\\w\\d\\.]+)-([\\d\\.]*\\d+)")
    }
}
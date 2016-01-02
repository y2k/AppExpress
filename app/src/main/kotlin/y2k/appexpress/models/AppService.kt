package y2k.appexpress.models

import android.content.Context
import rx.Observable
import y2k.appexpress.common.notNull
import java.io.File

//
// Created by y2k on 1/1/16.
//
class AppService(
    private val context: Context,
    private val packageService: PackageService,
    private val storageService: CloudStorageService) {

    fun getApps(): Observable<List<App>> {
        return storageService.list()
            .flatMap { Observable.from(it) }
            .flatMap { storageService.list(it) }
            .filter { !it.isEmpty() }
            .map { files ->
                files
                    .map { AppDescription(it) }
                    .filter { it.isValid }
                    .maxBy { it.serverVersion }
            }
            .notNull()
            .map { App(it, packageService.getVersion(it.packageName)) }
            .toList()
            .map { it.sortedBy { it.info.title } }
            .observeOn(UIScheduler.scheduler)
    }

    fun installApp(app: App) {
        val targetFile = File(context.cacheDir, app.info.packageName)
        storageService
            .downloadTo(File(""), targetFile)
            .map { packageService.install(targetFile) }
    }
}
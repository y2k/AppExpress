package y2k.appexpress.models

import android.content.Context
import rx.Observable
import y2k.appexpress.common.accumulate
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
            .flatMap({ storageService.list(it) }, 5)
            .map { files ->
                files
                    .map { AppDescription(it) }
                    .filter { it.isValid }
                    .maxBy { it.serverVersion }
            }
            .notNull()
            .map { App(it, packageService.getVersion(it.packageName)) }
            .accumulate()
            .observeOn(UIScheduler.scheduler)
    }

    fun installApp(app: App) {
        val targetFile = File(context.externalCacheDir, app.info.packageName + ".apk")
        storageService
            .downloadTo(app.info.file, targetFile)
            .map { packageService.install(targetFile) }
            .observeOn(UIScheduler.scheduler)
            .subscribe({
                // TODO
            }, { it.printStackTrace() })
    }
}
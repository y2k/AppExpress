package y2k.appexpress.models

import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import rx.Observable
import rx.schedulers.Schedulers
import y2k.appexpress.BuildConfig
import java.io.File
import java.util.*

//
// Created by y2k on 1/1/16.
//
class CloudStorageService {

    private val dbxClient by lazy {
        val config = DbxRequestConfig("AppExpress", "" + Locale.getDefault())
        DbxClientV2(config, BuildConfig.DROPBOX_ACCESS_TOKEN)
    }

    fun list(path: File? = null): Observable<List<File>> {
        return Observable
            .fromCallable {
                dbxClient.files
                    .listFolder(path?.absolutePath ?: "")
                    .entries
                    .map { if (path == null) File(it.name) else File(path, it.name) }
            }
            .subscribeOn(Schedulers.io())
    }

    fun downloadTo(src: File, target: File): Observable<Unit> {
        return Observable
            .fromCallable {
                target.outputStream().use {
                    dbxClient.files
                        .downloadBuilder(src.absolutePath)
                        .run(it)
                }
                Unit
            }
            .subscribeOn(Schedulers.io())
    }
}
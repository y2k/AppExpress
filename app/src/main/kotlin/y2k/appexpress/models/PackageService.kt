package y2k.appexpress.models

import android.content.Context
import android.content.pm.PackageManager

//
// Created by y2k on 1/1/16.
//
class PackageService(private val context: Context) {

    fun getVersion(packageName: String): Version? {
        try {
            val info = context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return Version(info.versionName)
        } catch (e: PackageManager.NameNotFoundException) {
            return null
        }
    }
}
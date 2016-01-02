package y2k.appexpress.models

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import java.io.File

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

    fun install(target: File) {
        var promptInstall = Intent(Intent.ACTION_VIEW);
        promptInstall.setClassName("com.android.packageinstaller", "com.android.packageinstaller.PackageInstallerActivity");

        val pm = context.packageManager;
        if (pm.queryIntentActivities(promptInstall, 0).isEmpty())
            promptInstall = Intent(Intent.ACTION_VIEW);

        promptInstall.setDataAndType(Uri.fromFile(target), "application/vnd.android.package-archive");
        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(promptInstall)
    }
}
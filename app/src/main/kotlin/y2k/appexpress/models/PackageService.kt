package y2k.appexpress.models

import android.content.Context
import android.content.pm.PackageManager

/**
 * Created by y2k on 1/1/16.
 */
class PackageService(private val context: Context) {

    fun checkIsInstalled(packageName: String): Boolean {
        try {
            context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }
}
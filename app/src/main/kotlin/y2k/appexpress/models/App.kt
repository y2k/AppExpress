package y2k.appexpress.models

//
// Created by y2k on 1/1/16.
//
class App(val title: String, val packageName: String, val serverVersion: Version) {

    var installedVersion: Version? = null

    val installed: Boolean
        get() = installedVersion != null

    val id: Long
        get() = packageName.hashCode().toLong()
}
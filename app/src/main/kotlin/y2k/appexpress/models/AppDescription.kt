package y2k.appexpress.models

import java.io.File

//
// Created by y2k on 1/2/16.
//
class AppDescription(val file: File) {

    val title: String
        get() = file.parentFile.name

    val isValid: Boolean
        get() = file.parentFile != null && InfoRegex.containsMatchIn(file.name)

    val packageName: String
        get() = InfoRegex.find(file.name)!!.groups[1]!!.value

    val serverVersion: Version
        get() = Version(InfoRegex.find(file.name)!!.groups[2]!!.value)

    companion object {

        private val InfoRegex = Regex("([\\w\\d\\.]+)-([\\d\\.]*\\d+)")
    }
}
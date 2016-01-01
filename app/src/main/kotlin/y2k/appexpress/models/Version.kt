package y2k.appexpress.models

//
// Created by y2k on 1/1/16.
//
class Version(val name: String) : Comparable<Version> {

    val parts = name.split('.')

    override fun compareTo(other: Version): Int {
        throw UnsupportedOperationException()
    }

    override fun toString(): String {
        return parts.joinToString(separator = ".")
    }
}
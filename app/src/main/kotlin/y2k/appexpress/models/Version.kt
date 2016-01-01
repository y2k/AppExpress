package y2k.appexpress.models

//
// Created by y2k on 1/1/16.
//
class Version(name: String) : Comparable<Version> {

    private val parts = name.split('.').map { it.toInt() }

    override fun compareTo(other: Version): Int {
        return parts
            .zip(other.parts, { l, r -> r - l })
            .firstOrNull { it != 0 } ?: other.parts.size - parts.size
    }

    override fun toString(): String {
        return parts.joinToString(separator = ".")
    }
}
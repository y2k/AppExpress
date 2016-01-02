package y2k.appexpress.models

import org.junit.Test

import org.junit.Assert.*

//
// Created by y2k on 1/2/16.
//
class VersionTest {

    @Test
    fun testCompareTo() {
        assertEquals(0, 10.compareTo(10))
        assertEquals(1, 10.compareTo(5))
        assertEquals(-1, 10.compareTo(15))

        assertEquals(0, Version("1.2.3").compareTo(Version("1.2.3")))
        assertEquals(1, Version("1.2.3").compareTo(Version("1.2")))
        assertEquals(-1, Version("1.2.3").compareTo(Version("1.3")))

        assertEquals(-1, Version("0.9.1").compareTo(Version("1")))
        assertEquals(1, Version("1").compareTo(Version("0.9.1")))

        // TODO:
        //        assertEquals(0, Version("1.2.3").compareTo(Version("1.2.3.0")))
        //        assertEquals(0, Version("1.2.3.0").compareTo(Version("1.2.3")))
    }
}
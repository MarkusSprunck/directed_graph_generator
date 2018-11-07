package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.IOException

class DiagramGeneratorTest {

    @Test
    @Throws(IOException::class)
    fun executeRunColorModeCluster() {
        // given
        val expected = "[ << aktiv>> \\n  <<Cloud-A>> \\n \\nApplication 01\\n (ID01)] as ID01  <<aaa-1>>"

        // when
        val result = DiagramGenerator.run("data_test.xlsx",
                "",
                "component",
                "",
                false,
                true,
                false,
                "cluster")

        // then
        assertEquals(expected, result.substring(13079, 13156))
    }

    @Test
    @Throws(IOException::class)
    fun executeRunColorModeStatus() {
        // given
        val expected = "[ << host-a>> \\n  <<AAA-2>> \\n \\nApplication 06\\n (ID06)] as ID06  <<aktiv>>"

        // when
        val result = DiagramGenerator.run("data_test.xlsx",
                "",
                "component",
                "",
                false,
                true,
                false,
                "status")

        // then
        assertEquals(expected, result.substring(12978, 13054))
    }


    @Test
    @Throws(IOException::class)
    fun executeRunColorModeLocation() {
        // given
        val expected = "[ << bbb-1>> \\n  <<aktiv>> \\n \\nApplication 03\\n (ID03)] as ID03  <<cloud-a>>"

        // when
        val result = DiagramGenerator.run("data_test.xlsx",
                "",
                "component",
                "",
                false,
                true,
                false,
                "location")

        // then
        assertEquals(expected, result.substring(13082, 13159))
    }

    @Test
    @Throws(IOException::class)
    fun executeRunColorModeLocationNotComplex() {
        // given
        val expected = """
            [Application 05\n (ID05)] as ID05  [[{Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 05}]]
             """.trimIndent()

        // when
        val result = DiagramGenerator.run("data_test.xlsx",
                "",
                "component",
                "",
                false,
                false,
                false,
                "location")

        // then
        assertTrue(result.contains( expected) )
    }


}

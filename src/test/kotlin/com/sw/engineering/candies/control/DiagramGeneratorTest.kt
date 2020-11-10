package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.IOException

class DiagramGeneratorTest {

    @Test
    @Throws(IOException::class)
    fun executeRunColorModeLocationNotComplex() {
        // given
        val expected = """
            {Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 05}
             """.trimIndent()

        // when
        val result = DiagramGenerator.run("data_test.xlsx",
                "",
                "component",
                "",
                false,
                false,
                false,
                "location", "")

        // then
        assertTrue(result.contains( expected) )
    }


}

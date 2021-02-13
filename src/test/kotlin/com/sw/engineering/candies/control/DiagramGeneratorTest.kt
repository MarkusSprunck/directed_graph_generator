package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.IOException


@SpringBootTest
class DiagramGeneratorTest  @Autowired constructor(var sut: DiagramGenerator) {

    @Test
    @Throws(IOException::class)
    fun executeRunColorModeLocationNotComplex() {
        // given
        val expected = """
            {Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 05}
             """.trimIndent()

        // when
        val result = sut.run("data_test.xlsx",
                "",
                "component",
                "",
                showLinks = false,
                showComplex = false,
                strict = false,
                colorMode = "location", diagramTitle = "")

        // then
        assertTrue(result.contains( expected) )
    }


}

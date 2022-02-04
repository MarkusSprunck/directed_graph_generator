package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.IOException
import kotlin.test.assertContains
import kotlin.test.assertEquals


@SpringBootTest
class DiagramGeneratorTest @Autowired constructor(var sut: DiagramGenerator) {

    @Test
    @Throws(IOException::class)
    fun executeRunColorModeLocationNotComplex() {

        // when
        val result = sut.run(
            "data_test.xlsx",
            "",
            "component",
            "",
            showLinks = true,
            showComplex = true,
            strict = false,
            colorMode = "location", diagramTitle = ""
        )

        // then
        assertTrue( result.contains("Application 06" ) )

    }


}

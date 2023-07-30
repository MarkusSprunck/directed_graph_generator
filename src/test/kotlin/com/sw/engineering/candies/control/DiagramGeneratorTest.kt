package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.IOException


@SpringBootTest
class DiagramGeneratorTest @Autowired constructor(var sut: DiagramGenerator) {

    @Test
    @Throws(IOException::class)
    fun executeRunColorModeLocationNotComplex() {

        // when
        val result = sut.run(
            excelFileName = "data_test.xlsx",
            sourceApplicationPattern = "",
            diagramType = "",
            showLinks = true,
            showComplex = true,
            strict = false,
            colorMode = "location",
            componentName = "",
            diagramTitle = ""
        )

        // then
        assertTrue(result.contains("Application 06"))

    }


}

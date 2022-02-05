package com.sw.engineering.candies.boundary

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class DiagramControllerTest @Autowired constructor(var sut: DiagramController) {

    @Test
    fun defaultMainRestController() {

        // when
        val result = sut.diagram(
            "",
            "",
            "",
            showLinks = false,
            showComplex = false,
            strict = false,
            colorMode = "",
            type = "",
            title = ""
        )

        // then
        assertEquals(
            "\n" +
                    "                <html>\n" +
                    "                    <head>\n" +
                    "                        <meta charset=\\\"utf-8\\\">\n" +
                    "                        <title>DGG</title>\n" +
                    "                        <link href='./main.css' rel='stylesheet' type='text/css'>\n" +
                    "                    </head>\n" +
                    "                    <body>\n" +
                    "                        <div>\n" +
                    "                            Find code and description of valid URL parameter\n" +
                    "                            <a href='https://github.com/MarkusSprunck/directed_graph_generator'>here</a>.\n" +
                    "                        </div>\n" +
                    "                    </body>\n" +
                    "                </html>\n" +
                    "                ", result
        )
    }

}

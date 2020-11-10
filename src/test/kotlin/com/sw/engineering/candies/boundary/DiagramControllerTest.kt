package com.sw.engineering.candies.boundary

import org.junit.Assert
import org.junit.Test

class DiagramControllerTest {

    @Test
    fun defaultMainRestController() {
        // given
        val sut = DiagramController()

        // when
        val result = sut.diagram("",
                "",
                "",
                false,
                false,
                false,
                "",
                "",
                "")

        // then
        Assert.assertEquals("\n" +
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
                "                ", result)
    }

}

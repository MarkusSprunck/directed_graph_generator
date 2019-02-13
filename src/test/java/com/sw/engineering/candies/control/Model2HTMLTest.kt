package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Node
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Model2HTMLTest {

    @Test
    fun addDependsUnique() {
        // given
        val sut = Node("Name A",
                "Name Long A",
                "Group XYZ",
                "XYZ",
                "",
                "")
        sut.addDepends("Name B")
        sut.addDepends("Name C")
        sut.addDependedOnBy("Name D")
        sut.addDependedOnBy("Name F")
        val expected = ("<h3>Name A - Name Long A</h3>" +
                "<b>Cluster</b>:<br/>XYZ<p/>" +
                "<b>Location:</b><br/><em><em><p/>" +
                "<b>Status:</b><br/><em><em><p/>" +
                "<b>Description:</b><br/><em>Group XYZ<em><p/" +
                "><b>Interface from:</b><p/>" +
                "<a href=\\\"#obj-Name B\\\" class=\\\"select-object\\\" data-name=\\\"Name B\\\">Name B</a> " +
                "<a href=\\\"#obj-Name C\\\" class=\\\"select-object\\\" data-name=\\\"Name C\\\">Name C</a> <p/" +
                "><b>Interface to:</b><p/>" +
                "<a href=\\\"#obj-Name D\\\" class=\\\"select-object\\\" data-name=\\\"Name D\\\">Name D</a> " +
                "<a href=\\\"#obj-Name F\\\" class=\\\"select-object\\\" data-name=\\\"Name F\\\">Name F</a> <p/>")

        // when
        val actual = Model2HTML.getNode(sut)

        // then
        assertEquals(expected, actual)
    }


    @Test
    fun addNoLinks() {
        // given
        val sut = Node("Name A",
                "Name Long A",
                "Group XYZ",
                "XYZ",
                "",
                "")
         val expected = ("<h3>Name A - Name Long A</h3>" +
                "<b>Cluster</b>:<br/>XYZ<p/>" +
                "<b>Location:</b><br/><em><em><p/>" +
                "<b>Status:</b><br/><em><em><p/>" +
                "<b>Description:</b><br/><em>Group XYZ<em><p/>")

        // when
        val actual = Model2HTML.getNode(sut)

        // then
        assertEquals(expected, actual)
    }


}

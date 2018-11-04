package entity

import com.sw.engineering.candies.entity.Node
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NodeTest {

    @Test
    fun addDependsUnique() {
        // given
        val sut = Node("Name A", "Name Long A", "Group XYZ", "XYZ", "","")
        sut.addDepends("Name B")
        sut.addDepends("Name C")
        sut.addDependedOnBy("Name D")
        sut.addDependedOnBy("Name F")
        val expected = """"Name A": {
    "name": "Name A",
    "type": "XYZ",
    "depends":["Name C", "Name B"],
    "dependedOnBy":["Name D", "Name F"],
    "docs": "<h3>Name A - Name Long A</h3><b>Cluster</b>:<br/>XYZ<p/><b>Location:</b><br/><em><em><p/><b>Status:</b><br/><em><em><p/><b>Description:</b><br/><em>Group XYZ<em><p/><b>Interface from:</b><p/><a href=\"#obj-Name B\" class=\"select-object\" data-name=\"Name B\">Name B</a> <a href=\"#obj-Name C\" class=\"select-object\" data-name=\"Name C\">Name C</a> <p/><b>Interface to:</b><p/><a href=\"#obj-Name D\" class=\"select-object\" data-name=\"Name D\">Name D</a> <a href=\"#obj-Name F\" class=\"select-object\" data-name=\"Name F\">Name F</a> <p/>"
}""".trimMargin()

        // when
        val actual = sut.toString()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun addDependsDuplicated() {
        // given
        val sut = Node("Name A", "Name Long A", "Group XYZ", "", "","")
        sut.addDepends("Name B")
        sut.addDepends("Name B")
        sut.addDepends("Name C")
        sut.addDependedOnBy("Name D")
        sut.addDependedOnBy("Name D")
        sut.addDependedOnBy("Name F")
        val expected = """"Name A": {
    "name": "Name A",
    "type": "",
    "depends":["Name C", "Name B"],
    "dependedOnBy":["Name D", "Name F"],
    "docs": "<h3>Name A - Name Long A</h3><b>Cluster</b>:<br/><p/><b>Location:</b><br/><em><em><p/><b>Status:</b><br/><em><em><p/><b>Description:</b><br/><em>Group XYZ<em><p/><b>Interface from:</b><p/><a href=\"#obj-Name B\" class=\"select-object\" data-name=\"Name B\">Name B</a> <a href=\"#obj-Name C\" class=\"select-object\" data-name=\"Name C\">Name C</a> <p/><b>Interface to:</b><p/><a href=\"#obj-Name D\" class=\"select-object\" data-name=\"Name D\">Name D</a> <a href=\"#obj-Name F\" class=\"select-object\" data-name=\"Name F\">Name F</a> <p/>"
}""".trimMargin()

        // when
        val actual = sut.toString()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun constructor() {
        // given
        val sut = Node("Name A", "Name Long A", "Group XYZ", "XXX","","")
        val expected = """
"Name A": {
    "name": "Name A",
    "type": "XXX",
    "depends":[],
    "dependedOnBy":[],
    "docs": "<h3>Name A - Name Long A</h3><b>Cluster</b>:<br/>XXX<p/><b>Location:</b><br/><em><em><p/><b>Status:</b><br/><em><em><p/><b>Description:</b><br/><em>Group XYZ<em><p/>"
}""".trimMargin()

        // when
        val actual = sut.toString()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun constructorDefault() {
        // given
        val sut = Node("","","","","","")
        val expected = """"": {
    "name": "",
    "type": "",
    "depends":[],
    "dependedOnBy":[],
    "docs": "<h3> - </h3><b>Cluster</b>:<br/><p/><b>Location:</b><br/><em><em><p/><b>Status:</b><br/><em><em><p/><b>Description:</b><br/><em><em><p/>"
}""".trimMargin()

        // when
        val actual = sut.toString()

        // then
        assertEquals(expected, actual)
    }

}

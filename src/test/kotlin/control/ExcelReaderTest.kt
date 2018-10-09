package control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExcelReaderTest {

    @Test
    fun readEmptyExcel() {
        // given
        val expected = "{\n}"

        // when
        val model = ExcelReader.parseExcelSheet("data_empty.xlsx", "")

        // then
        assertEquals(expected, model.toString())
    }

    @Test
    fun readNonExistingExcel() {
        // given
        val expected = "{\n}"

        // when
        val model = ExcelReader.parseExcelSheet("xxx.xlsx", "")

        // then
        assertEquals(expected, model.toString())
    }


    @Test
    fun readTestExcel() {
        // given
        val expected = """{
                        |"ID05": {
                        |    "name": "ID05",
                        |    "type": "C-BBB",
                        |    "depends":["ID06", "ID03"],
                        |    "dependedOnBy":["ID04"],
                        |    "docs": "<h3>ID05 - Test05</h3><b>Cluster</b>:<br/>C-BBB<p/><b>Location:</b><br/><em>Host-B<em><p/><b>Status:</b><br/><em>aktiv<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 05<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID03\" class=\"select-object\" data-name=\"ID03\">ID03</a> <a href=\"#obj-ID06\" class=\"select-object\" data-name=\"ID06\">ID06</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID04\" class=\"select-object\" data-name=\"ID04\">ID04</a> <p/>"
                        |},"ID06": {
                        |    "name": "ID06",
                        |    "type": "C-AAA",
                        |    "depends":["ID04", "ID01"],
                        |    "dependedOnBy":["ID05", "ID01"],
                        |    "docs": "<h3>ID06 - Test06</h3><b>Cluster</b>:<br/>C-AAA<p/><b>Location:</b><br/><em>Host-A<em><p/><b>Status:</b><br/><em>aktiv<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 06<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID04\" class=\"select-object\" data-name=\"ID04\">ID04</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID05\" class=\"select-object\" data-name=\"ID05\">ID05</a> <p/>"
                        |},"ID03": {
                        |    "name": "ID03",
                        |    "type": "C-BBB",
                        |    "depends":["ID01"],
                        |    "dependedOnBy":["ID05", "ID01"],
                        |    "docs": "<h3>ID03 - Test03</h3><b>Cluster</b>:<br/>C-BBB<p/><b>Location:</b><br/><em>Cloud-A<em><p/><b>Status:</b><br/><em>aktiv<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 03<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID05\" class=\"select-object\" data-name=\"ID05\">ID05</a> <p/>"
                        |},"ID04": {
                        |    "name": "ID04",
                        |    "type": "C-AAA",
                        |    "depends":["ID05", "ID02"],
                        |    "dependedOnBy":["ID06"],
                        |    "docs": "<h3>ID04 - Test04</h3><b>Cluster</b>:<br/>C-AAA<p/><b>Location:</b><br/><em>Cloud-A<em><p/><b>Status:</b><br/><em>aktiv<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 04<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID02\" class=\"select-object\" data-name=\"ID02\">ID02</a> <a href=\"#obj-ID05\" class=\"select-object\" data-name=\"ID05\">ID05</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID06\" class=\"select-object\" data-name=\"ID06\">ID06</a> <p/>"
                        |},"ID02": {
                        |    "name": "ID02",
                        |    "type": "C-AAA",
                        |    "depends":[],
                        |    "dependedOnBy":["ID04", "ID01"],
                        |    "docs": "<h3>ID02 - Test02</h3><b>Cluster</b>:<br/>C-AAA<p/><b>Location:</b><br/><em>Cloud-A<em><p/><b>Status:</b><br/><em>deprecated<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 02<em><p/><b>Interface to:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID04\" class=\"select-object\" data-name=\"ID04\">ID04</a> <p/>"
                        |}}""".trimMargin()

        // when
        val model = ExcelReader.parseExcelSheet("data_test.xlsx", "")

        // then
        assertEquals(expected, model.toString())
    }


}

package control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExcelReaderTest {

    @Test
    fun readEmptyExcel() {
        // given
        val expected = "{\n}"

        // when
        val model = ExcelReader.parseExcelSheet("data_empty.xlsx", "", false, "cluster")

        // then
        assertEquals(expected, model.toString())
    }

    @Test
    fun readNonExistingExcel() {
        // given
        val expected = "{\n}"

        // when
        val model = ExcelReader.parseExcelSheet("xxx.xlsx", "", false, "cluster")

        // then
        assertEquals(expected, model.toString())
    }


    @Test
    fun readTestExcel() {
        // given
        val expected = """
{
"ID05": {
    "name": "ID05",
    "type": "hoch",
    "depends":["ID06", "ID03"],
    "dependedOnBy":["ID04"],
    "docs": "<h3>ID05 - Test05</h3><b>Cluster</b>:<br/>hoch<p/><b>Location:</b><br/><em>aktiv<em><p/><b>Status:</b><br/><em>C-BBB<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 05<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID03\" class=\"select-object\" data-name=\"ID03\">ID03</a> <a href=\"#obj-ID06\" class=\"select-object\" data-name=\"ID06\">ID06</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID04\" class=\"select-object\" data-name=\"ID04\">ID04</a> <p/>"
},"ID06": {
    "name": "ID06",
    "type": "niedrig",
    "depends":["ID04", "ID01"],
    "dependedOnBy":["ID05", "ID01"],
    "docs": "<h3>ID06 - Test06</h3><b>Cluster</b>:<br/>niedrig<p/><b>Location:</b><br/><em>aktiv<em><p/><b>Status:</b><br/><em>C-AAA<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 06<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID04\" class=\"select-object\" data-name=\"ID04\">ID04</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID05\" class=\"select-object\" data-name=\"ID05\">ID05</a> <p/>"
},"ID03": {
    "name": "ID03",
    "type": "hoch",
    "depends":["ID01"],
    "dependedOnBy":["ID05", "ID01"],
    "docs": "<h3>ID03 - Test03</h3><b>Cluster</b>:<br/>hoch<p/><b>Location:</b><br/><em>aktiv<em><p/><b>Status:</b><br/><em>C-BBB<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 03<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID05\" class=\"select-object\" data-name=\"ID05\">ID05</a> <p/>"
},"ID04": {
    "name": "ID04",
    "type": "hoch",
    "depends":["ID05", "ID02"],
    "dependedOnBy":["ID06"],
    "docs": "<h3>ID04 - Test04</h3><b>Cluster</b>:<br/>hoch<p/><b>Location:</b><br/><em>aktiv<em><p/><b>Status:</b><br/><em>C-AAA<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 04<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID02\" class=\"select-object\" data-name=\"ID02\">ID02</a> <a href=\"#obj-ID05\" class=\"select-object\" data-name=\"ID05\">ID05</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID06\" class=\"select-object\" data-name=\"ID06\">ID06</a> <p/>"
},"ID01": {
    "name": "ID01",
    "type": "hoch",
    "depends":["ID06", "ID02", "ID03"],
    "dependedOnBy":["ID06", "ID03"],
    "docs": "<h3>ID01 - Test01</h3><b>Cluster</b>:<br/>hoch<p/><b>Location:</b><br/><em>aktiv<em><p/><b>Status:</b><br/><em>C-AAA<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 01<em><p/><b>Interface from:</b><p/><a href=\"#obj-ID02\" class=\"select-object\" data-name=\"ID02\">ID02</a> <a href=\"#obj-ID03\" class=\"select-object\" data-name=\"ID03\">ID03</a> <a href=\"#obj-ID06\" class=\"select-object\" data-name=\"ID06\">ID06</a> <p/><b>Interface to:</b><p/><a href=\"#obj-ID03\" class=\"select-object\" data-name=\"ID03\">ID03</a> <a href=\"#obj-ID06\" class=\"select-object\" data-name=\"ID06\">ID06</a> <p/>"
},"ID02": {
    "name": "ID02",
    "type": "hoch",
    "depends":[],
    "dependedOnBy":["ID04", "ID01"],
    "docs": "<h3>ID02 - Test02</h3><b>Cluster</b>:<br/>hoch<p/><b>Location:</b><br/><em>deprecated<em><p/><b>Status:</b><br/><em>C-AAA<em><p/><b>Description:</b><br/><em>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation. 02<em><p/><b>Interface to:</b><p/><a href=\"#obj-ID01\" class=\"select-object\" data-name=\"ID01\">ID01</a> <a href=\"#obj-ID04\" class=\"select-object\" data-name=\"ID04\">ID04</a> <p/>"
}}""".trimMargin()

        // when
        val model = ExcelReader.parseExcelSheet("data_test.xlsx", "", false, "cluster")

        // then
        assertEquals(expected, model.toString())
    }


}

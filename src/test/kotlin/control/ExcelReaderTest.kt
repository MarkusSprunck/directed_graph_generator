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
        val expected = """
                        |{
                        |"10A": {
                        |    "name": "Name A",
                        |    "type": "Cluster #1",
                        |    "depends":["10F"],
                        |    "dependedOnBy":["10C", "10B"],
                        |    "docs": "<h3>10A - Name A</h3>Cluster #1<p/><em>AAA_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10F\" class=\"select-object\" data-name=\"10F\">10F</a> <p/><b>Interface to:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <a href=\"#obj-10C\" class=\"select-object\" data-name=\"10C\">10C</a> <p/>"
                        |},"10B": {
                        |    "name": "Name B",
                        |    "type": "Cluster #2",
                        |    "depends":["10A"],
                        |    "dependedOnBy":["10D", "10G", "10I", "10H"],
                        |    "docs": "<h3>10B - Name B</h3>Cluster #2<p/><em>BBB_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/><b>Interface to:</b><p/><a href=\"#obj-10D\" class=\"select-object\" data-name=\"10D\">10D</a> <a href=\"#obj-10G\" class=\"select-object\" data-name=\"10G\">10G</a> <a href=\"#obj-10H\" class=\"select-object\" data-name=\"10H\">10H</a> <a href=\"#obj-10I\" class=\"select-object\" data-name=\"10I\">10I</a> <p/>"
                        |},"10C": {
                        |    "name": "Name C",
                        |    "type": "Cluster #3",
                        |    "depends":["10A"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10C - Name C</h3>Cluster #3<p/><em>CCC_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/>"
                        |},"10D": {
                        |    "name": "Name D",
                        |    "type": "Cluster #5",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10D - Name D</h3>Cluster #5<p/><em>DDD_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |},"10E": {
                        |    "name": "Name E",
                        |    "type": "Cluster #4",
                        |    "depends":["10F"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10E - Name E</h3>Cluster #4<p/><em>EEE_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10F\" class=\"select-object\" data-name=\"10F\">10F</a> <p/>"
                        |},"10F": {
                        |    "name": "Name F",
                        |    "type": "Cluster #4",
                        |    "depends":[],
                        |    "dependedOnBy":["10A", "10E", "10J"],
                        |    "docs": "<h3>10F - Name F</h3>Cluster #4<p/><em>FFF_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface to:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <a href=\"#obj-10E\" class=\"select-object\" data-name=\"10E\">10E</a> <a href=\"#obj-10J\" class=\"select-object\" data-name=\"10J\">10J</a> <p/>"
                        |},"10G": {
                        |    "name": "Name G",
                        |    "type": "Cluster #2",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10G - Name G</h3>Cluster #2<p/><em>GGG_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |},"10H": {
                        |    "name": "Name H",
                        |    "type": "Cluster #2",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10H - Name H</h3>Cluster #2<p/><em>HHH_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |},"10I": {
                        |    "name": "Name I",
                        |    "type": "Cluster #2",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10I - Name I</h3>Cluster #2<p/><em>III_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |},"10J": {
                        |    "name": "Name J",
                        |    "type": "Cluster #4",
                        |    "depends":["10F"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10J - Name J</h3>Cluster #4<p/><em>JJJ_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10F\" class=\"select-object\" data-name=\"10F\">10F</a> <p/>"
                        |}}""".trimMargin()

        // when
        val model = ExcelReader.parseExcelSheet("data_test.xlsx", "")

        // then
        assertEquals(expected, model.toString())
    }

    @Test
    fun readTestExcelWithFilterMinimal() {
        // given
        val expected = """
            |{
            |"10A": {
            |    "name": "Name A",
            |    "type": "Cluster #1",
            |    "depends":["10F"],
            |    "dependedOnBy":["10C", "10B"],
            |    "docs": "<h3>10A - Name A</h3>Cluster #1<p/><em>AAA_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10F\" class=\"select-object\" data-name=\"10F\">10F</a> <p/><b>Interface to:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <a href=\"#obj-10C\" class=\"select-object\" data-name=\"10C\">10C</a> <p/>"
            |},"10B": {
            |    "name": "Name B",
            |    "type": "Cluster #2",
            |    "depends":["10A"],
            |    "dependedOnBy":[],
            |    "docs": "<h3>10B - Name B</h3>Cluster #2<p/><em>BBB_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/>"
            |},"10C": {
            |    "name": "Name C",
            |    "type": "Cluster #3",
            |    "depends":["10A"],
            |    "dependedOnBy":[],
            |    "docs": "<h3>10C - Name C</h3>Cluster #3<p/><em>CCC_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/>"
            |},"10F": {
            |    "name": "Name F",
            |    "type": "Cluster #4",
            |    "depends":[],
            |    "dependedOnBy":["10A"],
            |    "docs": "<h3>10F - Name F</h3>Cluster #4<p/><em>FFF_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface to:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/>"
            |}}
                       """.trimMargin()

        // when
        val model = ExcelReader.parseExcelSheet("data_test.xlsx", "10A")

        // then
        assertEquals(expected, model.toString())
    }


    @Test
    fun readTestExcelWithFilterComplex() {
        // given
        val expected = """
                        |{
                        |"10A": {
                        |    "name": "Name A",
                        |    "type": "Cluster #1",
                        |    "depends":["10F"],
                        |    "dependedOnBy":["10C", "10B"],
                        |    "docs": "<h3>10A - Name A</h3>Cluster #1<p/><em>AAA_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10F\" class=\"select-object\" data-name=\"10F\">10F</a> <p/><b>Interface to:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <a href=\"#obj-10C\" class=\"select-object\" data-name=\"10C\">10C</a> <p/>"
                        |},"10B": {
                        |    "name": "Name B",
                        |    "type": "Cluster #2",
                        |    "depends":["10A"],
                        |    "dependedOnBy":["10D", "10G", "10I", "10H"],
                        |    "docs": "<h3>10B - Name B</h3>Cluster #2<p/><em>BBB_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/><b>Interface to:</b><p/><a href=\"#obj-10D\" class=\"select-object\" data-name=\"10D\">10D</a> <a href=\"#obj-10G\" class=\"select-object\" data-name=\"10G\">10G</a> <a href=\"#obj-10H\" class=\"select-object\" data-name=\"10H\">10H</a> <a href=\"#obj-10I\" class=\"select-object\" data-name=\"10I\">10I</a> <p/>"
                        |},"10C": {
                        |    "name": "Name C",
                        |    "type": "Cluster #3",
                        |    "depends":["10A"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10C - Name C</h3>Cluster #3<p/><em>CCC_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/>"
                        |},"10D": {
                        |    "name": "Name D",
                        |    "type": "Cluster #5",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10D - Name D</h3>Cluster #5<p/><em>DDD_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |},"10F": {
                        |    "name": "Name F",
                        |    "type": "Cluster #4",
                        |    "depends":[],
                        |    "dependedOnBy":["10A"],
                        |    "docs": "<h3>10F - Name F</h3>Cluster #4<p/><em>FFF_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface to:</b><p/><a href=\"#obj-10A\" class=\"select-object\" data-name=\"10A\">10A</a> <p/>"
                        |},"10G": {
                        |    "name": "Name G",
                        |    "type": "Cluster #2",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10G - Name G</h3>Cluster #2<p/><em>GGG_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |},"10H": {
                        |    "name": "Name H",
                        |    "type": "Cluster #2",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10H - Name H</h3>Cluster #2<p/><em>HHH_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |},"10I": {
                        |    "name": "Name I",
                        |    "type": "Cluster #2",
                        |    "depends":["10B"],
                        |    "dependedOnBy":[],
                        |    "docs": "<h3>10I - Name I</h3>Cluster #2<p/><em>III_Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. <em><p/><b>Interface from:</b><p/><a href=\"#obj-10B\" class=\"select-object\" data-name=\"10B\">10B</a> <p/>"
                        |}}""".trimMargin()

        // when
        val model = ExcelReader.parseExcelSheet("data_test.xlsx", "10A|10B")

        // then
        assertEquals(expected, model.toString())
    }

}

package control

import com.sw.engineering.candies.control.Model2PlantUml
import com.sw.engineering.candies.entity.Model
import com.sw.engineering.candies.entity.Node
import org.junit.Assert.assertEquals
import org.junit.Test

class Model2PlantUmlTest {

    @Test
    fun toUmlEmpty() {
        // given
        val sut = Model()
        val expected = """
                |
                |
                |
                """.trimMargin()

        // when
        val actual =  Model2PlantUml.toPlantUmlModel(sut)

        // then
        assertEquals(expected, actual)
    }


    @Test
    fun toUmlTwoNodes() {
        // given
        val sut = Model()
        val expected = """
                |
                |skinparam componentBorderColor<<s0b>> #d50000
                |skinparam componentBorderColor<<s0a>> #ffd600
                |
                |[ << s2b>> \n  <<S1B>> \n \nNodeB\n (IDB)] as IDB  <<s0b>>  [[{DesB}]]
                |[ << s2a>> \n  <<S1A>> \n \nNodeA\n (IDA)] as IDA  <<s0a>>  [[{DesA}]]
                |
                """.trimMargin()

        // when
        sut.setNode("A", Node("IDA", "NodeA", "DesA", "S0A", "S1A", "S2A"))
        sut.setNode("B", Node("IDB", "NodeB", "DesB", "S0B", "S1B", "S2B"))
        val actual =  Model2PlantUml.toPlantUmlModel(sut)

        // then
        assertEquals(expected, actual)
    }


    @Test
    fun toUmlOneLinkShow() {
        // given
        val sut = Model()
        val expected = """
                |
                |skinparam componentBorderColor<<s0b>> #d50000
                |skinparam componentBorderColor<<s0a>> #ffd600
                |
                |[ << s2b>> \n  <<S1B>> \n \nNodeB\n (IDB)] as IDB  <<s0b>>  [[{DesB}]]
                |[ << s2a>> \n  <<S1A>> \n \nNodeA\n (IDA)] as IDA  <<s0a>>  [[{DesA}]]
                |
                |[A] ..> [IDB] : Link comment
                |[IDA] ..> [B] : Link comment
                |
                """.trimMargin()

        // when
        sut.setNode("A", Node("IDA", "NodeA", "DesA", "S0A", "S1A", "S2A"))
        sut.setNode("B", Node("IDB", "NodeB", "DesB", "S0B", "S1B", "S2B"))
        sut.getNode("A")?.addDependedOnBy("B", "Link comment")
        sut.getNode("B")?.addDepends("A", "Link comment")
        val actual =  Model2PlantUml.toPlantUmlModel(sut, "", true)

        // then
        assertEquals(expected, actual)
    }


    @Test
    fun toUmlOneLinkNoShow() {
        // given
        val sut = Model()
        val expected = """
                |
                |skinparam componentBorderColor<<s0b>> #d50000
                |skinparam componentBorderColor<<s0a>> #ffd600
                |
                |[ << s2b>> \n  <<S1B>> \n \nNodeB\n (IDB)] as IDB  <<s0b>>  [[{DesB}]]
                |[ << s2a>> \n  <<S1A>> \n \nNodeA\n (IDA)] as IDA  <<s0a>>  [[{DesA}]]
                |
                """.trimMargin()

        // when
        sut.setNode("A", Node("IDA", "NodeA", "DesA", "S0A", "S1A", "S2A"))
        sut.setNode("B", Node("IDB", "NodeB", "DesB", "S0B", "S1B", "S2B"))
        sut.getNode("A")?.addDependedOnBy("B", "Link comment")
        sut.getNode("B")?.addDepends("A", "Link comment")
        val actual =  Model2PlantUml.toPlantUmlModel(sut, "", false)

        // then
        assertEquals(expected, actual)
    }


}
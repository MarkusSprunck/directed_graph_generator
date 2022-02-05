package com.sw.engineering.candies.control

import com.sw.engineering.candies.entity.Model
import com.sw.engineering.candies.entity.Node
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Model2PlantUmlTest @Autowired constructor(var model2PlantUml: Model2PlantUml) {

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
        val actual = model2PlantUml.toPlantUmlModel(sut)

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
                |[ << s2b>> \n  <<S1B>> \n \nNodeB\n (IDB)] as IDB  <<s0b>>  [[http://localhost:8444/diagram?type=component&file=data.xlsx&strict=false&diagramTitle=UML-Component&showLinks=true&showComplex=true&colorMode=location&filter=IDB{DesB}]]
                |[ << s2a>> \n  <<S1A>> \n \nNodeA\n (IDA)] as IDA  <<s0a>>  [[http://localhost:8444/diagram?type=component&file=data.xlsx&strict=false&diagramTitle=UML-Component&showLinks=true&showComplex=true&colorMode=location&filter=IDA{DesA}]]
                |
                """.trimMargin()

        // when
        sut.setNode("A", Node("IDA", "NodeA", "DesA", "S0A", "S1A", "S2A"))
        sut.setNode("B", Node("IDB", "NodeB", "DesB", "S0B", "S1B", "S2B"))
        val actual = model2PlantUml.toPlantUmlModel(sut)

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
                |[ << s2b>> \n  <<S1B>> \n \nNodeB\n (IDB)] as IDB  <<s0b>>  [[http://localhost:8444/diagram?type=component&file=data.xlsx&strict=false&diagramTitle=UML-Component&showLinks=true&showComplex=true&colorMode=location&filter=IDB{DesB}]]
                |[ << s2a>> \n  <<S1A>> \n \nNodeA\n (IDA)] as IDA  <<s0a>>  [[http://localhost:8444/diagram?type=component&file=data.xlsx&strict=false&diagramTitle=UML-Component&showLinks=true&showComplex=true&colorMode=location&filter=IDA{DesA}]]
                |
                """.trimMargin()

        // when
        sut.setNode("A", Node("IDA", "NodeA", "DesA", "S0A", "S1A", "S2A"))
        sut.setNode("B", Node("IDB", "NodeB", "DesB", "S0B", "S1B", "S2B"))
        sut.getNode("A")?.addDependedOnBy("B", "Link comment")
        sut.getNode("B")?.addDepends("A", "Link comment")
        val actual = model2PlantUml.toPlantUmlModel(sut, "", false)

        // then
        assertEquals(expected, actual)
    }


}

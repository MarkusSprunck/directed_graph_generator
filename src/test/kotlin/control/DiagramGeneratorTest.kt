package control

import java.io.IOException

import control.DiagramGenerator
import control.FileUtil
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class DiagramGeneratorTest {

    @Test
    @Throws(IOException::class)
    fun executeRun() {
        // given
        val expected = 274502

        // when
        val result = DiagramGenerator.run("data_test.xlsx", "")

        // then
        assertEquals(expected, result.length)
    }

}

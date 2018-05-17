package control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.IOException

class DiagramGeneratorTest {

    @Test
    @Throws(IOException::class)
    fun executeRun() {
        // given
        val expected = 291871

        // when
        val result = DiagramGenerator.run("data_test.xlsx", "")

        // then
        assertEquals(expected, result.length)
    }

}

package control

import com.sw.engineering.candies.control.DiagramGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.IOException

class DiagramGeneratorTest {

    @Test
    @Throws(IOException::class)
    fun executeRun() {
        // given
        val expected = 10678

        // when
        val result = DiagramGenerator.run("data_test.xlsx",
                "",
                "component",
                "",
                false,
                false,
                false,
                "stereotypeFirst")

        // then
        assertEquals(expected, result.length)
    }

}

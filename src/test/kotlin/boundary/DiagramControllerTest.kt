package boundary

import com.sw.engineering.candies.boundary.boundary.DiagramController
import org.junit.Assert
import org.junit.Test

class DiagramControllerTest {

    @Test
    fun defaultMainRestController() {
        // given
        val sut = DiagramController()

        // when
        val result = sut.diagram("",
                "",
                "",
                false,
                false,
                "",
                "")

        // then
        Assert.assertEquals("<h3>Enter valid URL parameter for type {'graph', 'component'}</h3>", result)
    }

}
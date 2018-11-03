
import com.sw.engineering.candies.Application
import org.junit.Test
import org.springframework.boot.web.embedded.tomcat.ConnectorStartFailedException

class ApplicationTest {

    @Test(expected = ConnectorStartFailedException::class )
    fun mainStartTwoTimes() {
        // given
        val stingArray = arrayOf("")

        // when
        Application.main(stingArray)
        Application.main(stingArray)

    }

}
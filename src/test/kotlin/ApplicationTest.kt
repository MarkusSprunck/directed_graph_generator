
import com.sw.engineering.candies.Application
import org.junit.Test
import org.springframework.boot.web.embedded.tomcat.ConnectorStartFailedException
import org.springframework.context.ApplicationContextException

class ApplicationTest {

    @Test(expected = ApplicationContextException::class )
    fun mainStartTwoTimes() {
        // given
        val stingArray = arrayOf("")

        // when
        Application.main(stingArray)
        Application.main(stingArray)

    }

}

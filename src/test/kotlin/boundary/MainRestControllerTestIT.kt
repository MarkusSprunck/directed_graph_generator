package boundary

import control.MainApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith( SpringRunner::class )
@SpringBootTest(classes= [MainApplication::class] ,  webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT )
class MainRestControllerTestIT {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun defaultMainRestController() {
        // given
        val url = "http://localhost:8080/diagram"

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java)

        // then
        Assert.assertNotNull(result)
        Assert.assertEquals(274502, result.body.length)
        Assert.assertEquals(104, result.headers.toString().length)
        Assert.assertEquals("!e||\"object\"!==x.type(e)||", result.body.subSequence( 5913, 5939))
    }


    @Test
    fun defaultMainRestControllerParameter() {
        // given
        val url = "http://localhost:8080/diagram?filter=10A"

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java)

        // then
        Assert.assertNotNull(result)
        Assert.assertEquals(271668, result.body.length)
        Assert.assertEquals(104, result.headers.toString().length)
        Assert.assertEquals("!e||\"object\"!==x.type(e)||", result.body.subSequence( 5913, 5939))
    }


}
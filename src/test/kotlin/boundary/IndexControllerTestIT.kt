package boundary

import control.MainApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [MainApplication::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IndexControllerTestIT {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun defaultIndexController() {
        // given
        val url = "http://localhost:8080/"

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java)

        // then
        Assert.assertNotNull(result)
        Assert.assertEquals(852, result.body.length)
        Assert.assertEquals(182, result.headers.toString().length)
        Assert.assertEquals("<title>Graph Generator</title>", result.body.subSequence(71, 101))
    }


}
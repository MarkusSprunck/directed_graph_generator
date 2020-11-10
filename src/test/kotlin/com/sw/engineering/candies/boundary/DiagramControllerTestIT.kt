package com.sw.engineering.candies.boundary

import com.sw.engineering.candies.control.MainApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [MainApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiagramControllerTestIT {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Value("\${local.server.port}")
    protected var localPort: Int = 0


    @Test
    fun defaultMainRestController() {
        // given
        val url = "http://localhost:" + localPort +"/diagram"

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java)

        // then
        Assert.assertNotNull(result)
        Assert.assertEquals(154, result.headers.toString().length)
        Assert.assertEquals("unction(){return this.eq(-", result.body.subSequence(5913, 5939))
    }


    @Test
    fun defaultMainRestControllerParameter() {
        // given
        val url = "http://localhost:" + localPort +"/diagram?filter=10A"

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java)

        // then
        Assert.assertNotNull(result)
        Assert.assertEquals(287434, result.body.length)
        Assert.assertEquals("unction(){return this.eq(-", result.body.subSequence(5913, 5939))
    }


}

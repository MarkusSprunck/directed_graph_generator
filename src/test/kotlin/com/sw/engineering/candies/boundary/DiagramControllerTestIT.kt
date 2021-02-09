package com.sw.engineering.candies.boundary

import com.sw.engineering.candies.control.MainApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.util.HashMap
import kotlin.reflect.jvm.jvmName

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
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
        val params: Map<String, String> = HashMap()

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java, params)

        // then
        assertNotNull(result)
        assertEquals(154, result.headers.toString().length)
        assertEquals("unction(){return this.eq(-", result.body.subSequence(5913, 5939))
    }


    @Test
    fun defaultMainRestControllerParameter() {
        // given
        val url = "http://localhost:" + localPort +"/diagram"
        val params: MutableMap<String, String> = HashMap()
        params["filter"] = "10A"

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java, params)

        // then
        assertNotNull(result)
        assertEquals(291576, result.body.length)
        assertEquals("unction(){return this.eq(-", result.body.subSequence(5913, 5939))
    }

}

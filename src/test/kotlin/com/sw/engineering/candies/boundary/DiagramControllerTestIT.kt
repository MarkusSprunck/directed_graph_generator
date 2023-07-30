package com.sw.engineering.candies.boundary

import com.sw.engineering.candies.Application
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@Suppress(
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
@SpringBootTest(
    classes = [Application::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class DiagramControllerTestIT {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Value("\${local.server.port}")
    protected var localPort: Int = 0


    @Test
    fun defaultMainRestController() {
        // given
        val url = "http://localhost:$localPort/diagram"
        val params: Map<String, String> = HashMap()

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java, params)

        // then
        assertNotNull(result)
        assertEquals(153, result.headers.toString().length)
    }


    @Test
    fun defaultMainRestControllerParameter() {
        // given
        val url = "http://localhost:$localPort/diagram"
        val params: MutableMap<String, String> = HashMap()
        params["filter"] = "10A"

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java, params)

        // then
        assertNotNull(result)
        assertEquals(17372 , result.body?.length!!)
    }

}

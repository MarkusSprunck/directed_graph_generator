package com.sw.engineering.candies.boundary

import com.sw.engineering.candies.Application
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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
class IndexControllerTestIT {

    @Value("\${local.server.port}")
    protected var localPort: Int = 0

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun defaultIndexController() {
        // given
        val url = "http://localhost:$localPort/"
        val params: Map<String, String> = HashMap()

        // when
        val result = testRestTemplate.getForEntity(url, String::class.java, params)

        // then
        assertNotNull(result)
        assertEquals(1510, result.body?.length)
    }

}

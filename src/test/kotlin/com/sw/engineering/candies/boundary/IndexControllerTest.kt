package com.sw.engineering.candies.boundary

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class IndexControllerTest {


    @Test
    fun index() {

        // given
        val   sut = IndexController()

        // when
        val result = sut.index()

        // then
        assertEquals("index.html", result)
     }


}

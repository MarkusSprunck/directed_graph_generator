package com.sw.engineering.candies.boundary

import org.junit.Assert
import org.junit.Test

class IndexControllerTest {


    @Test
    fun index() {

        // given
        val   sut = IndexController()

        // when
        val result = sut.index()

        // then
        Assert.assertEquals("index.html", result)
     }


}
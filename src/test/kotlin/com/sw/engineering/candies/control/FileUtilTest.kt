package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FileUtilTest {

    @Test
    fun readTemplateCss() {
        // given
        val expected = 139

        // when
        val result = FileUtil.load("static/main.css")

        // then
        assertEquals(expected, result.length)
    }



}

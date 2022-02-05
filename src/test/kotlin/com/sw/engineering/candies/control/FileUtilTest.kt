package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FileUtilTest {

    @Test
    fun readTemplateCss() {
        // given
        val expected = 3253

        // when
        val result = FileUtil.load("static/main.css")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateJs() {
        // given
        val expected = 15612

        // when
        val result = FileUtil.load("templates/main.js")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateHtml() {
        // given
        val expected = 2568

        // when
        val result = FileUtil.load("templates/main.html")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateJQuery() {
        // given
        val expected = 410631

        // when
        val result = FileUtil.load("libs/jquery-3.6.0.js")

        // then
        assertEquals(expected, result.length)
    }


    @Test
    fun readTemplateD3() {
        // given
        val expected = 151749

        // when
        val result = FileUtil.load("libs/d3.v3.min.js")

        // then
        assertEquals(expected, result.length)
    }


}

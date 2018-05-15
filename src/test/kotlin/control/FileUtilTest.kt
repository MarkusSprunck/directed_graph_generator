package control

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class FileUtilTest {

    @Test
    fun readTemplateHtml() {
        // given
        val expected = 2921

        // when
        val result = FileUtil.load("templates/main.html")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateCss() {
        // given
        val expected = 1365

        // when
        val result = FileUtil.load("static/main.css")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateJs() {
        // given
        val expected = 15485

        // when
        val result = FileUtil.load("templates/main.js")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateJQuery() {
        // given
        val expected = 93112

        // when
        val result = FileUtil.load("libs/jquery-1.10.2.min.js")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateJQueryBrowser() {
        // given
        val expected = 2612

        // when
        val result = FileUtil.load("libs/jquery.browser.min.js")

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

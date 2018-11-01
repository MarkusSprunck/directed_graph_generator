package control

import com.sw.engineering.candies.control.FileUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FileUtilTest {

    @Test
    fun readTemplateHtml() {
        // given
        val expected = 2715

        // when
        val result = FileUtil.load("templates/main.html")

        // then
        assertEquals(expected, result.length)
    }

    @Test
    fun readTemplateCss() {
        // given
        val expected = 3255

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

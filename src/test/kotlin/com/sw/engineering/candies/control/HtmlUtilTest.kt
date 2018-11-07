package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HtmlUtilTest {

    @Test
    fun escapeSpecialCharacters() {
        // given
        val expected = " &nbsp; &nbsp; &nbsp; &nbsp;&lt; &gt; &amp; &quot; <br> &#598;"

        // when
        val actual = HtmlUtil.escape(" \t  < > & \" \n \u0256")

        // then
        assertEquals(expected, actual)
    }

}

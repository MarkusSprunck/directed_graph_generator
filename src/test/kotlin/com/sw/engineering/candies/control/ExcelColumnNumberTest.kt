package com.sw.engineering.candies.control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExcelColumnNumberTest {

    @Test
    fun getValueOfFirstAppId() {
        // given
        val sut = ExcelColumnNumberLinks.FIRST_APP_ID

        // when
        val actual = sut.value

        // then
        assertEquals(Integer.valueOf(0), actual)
    }


    @Test
    fun getValueOfSecondAppId() {
        // given
        val sut = ExcelColumnNumberLinks.SECOND_APP_ID

        // when
        val actual = sut.value

        // then
        assertEquals(Integer.valueOf(2), actual)
    }

    @Test
    fun countNumberOfValues() {
        // given
        val expected = 8

        // when
        val actual = ExcelColumnNumberLinks.values()

        // then
        assertEquals(expected, actual.size)
    }

}

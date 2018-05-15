package control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExcelColumnNumberTest {

    @Test
    fun getValueOfFirstAppId() {
        // given
        val sut = ExcelColumnNumber.FIRST_APP_ID

        // when
        val actual = sut.value

        // then
        assertEquals(Integer.valueOf(0), actual)
    }


    @Test
    fun getValueOfSecondAppId() {
        // given
        val sut = ExcelColumnNumber.SECOND_APP_ID

        // when
        val actual = sut.value

        // then
        assertEquals(Integer.valueOf(4), actual)
    }

    @Test
    fun countNumberOfValues() {
        // given
        val expected = 8

        // when
        val actual = ExcelColumnNumber.values()

        // then
        assertEquals(expected, actual.size)
    }

}

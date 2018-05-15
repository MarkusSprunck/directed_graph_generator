package control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExcelColumnIDTest {

    @Test
    fun getValueOfFirstAppId() {
        // given
        val sut = ExcelColumnID.FIRST_APP_ID

        // when
        val actual = sut.value

        // then
        assertEquals(Integer.valueOf(0), actual)
    }


    @Test
    fun getValueOfSecondAppId() {
        // given
        val sut = ExcelColumnID.SECOND_APP_ID

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
        val actual = ExcelColumnID.values()

        // then
        assertEquals(expected, actual.size)
    }

}

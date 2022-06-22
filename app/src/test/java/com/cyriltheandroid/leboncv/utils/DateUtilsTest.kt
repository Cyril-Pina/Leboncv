package com.cyriltheandroid.leboncv.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Ignore
import org.junit.Test
import java.util.*

class DateUtilsTest {

    @Test
    fun `getDatesDifferenceInHour() with two equal dates`() {
        val date1 = Date(1486045086000L)
        val date2 = Date(1486045086000L)
        assertThat(getDatesDifferenceInHour(date1, date2)).isEqualTo(0)
    }

    @Test
    fun `getDatesDifferenceInHour() with first date higher than second date`() {
        val date1 = Date(1593699486000L)
        val date2 = Date(1486045086000L)
        assertThat(getDatesDifferenceInHour(date1, date2)).isLessThan(0)
    }

    @Test
    fun `getDatesDifferenceInHour() with first date lower than second date`() {
        val date1 = Date(1486045086000L)
        val date2 = Date(1593699486000L)
        assertThat(getDatesDifferenceInHour(date1, date2)).isGreaterThan(0)
    }

    @Ignore("Different UTC on CI/CD.")
    @Test
    fun getTimestampFormattedDateAsStringTest() {
        val date = Date(1593699486000L)
        assertThat(DateUtils.getTimestampFormattedDateAsString(date)).isEqualTo("02/07/2020 à 16:18")
    }

    @Ignore("Different UTC on CI/CD.")
    @Test
    fun getMessageFormattedDateAsStringTest() {
        val date = Date(1593699486000L)
        assertThat(DateUtils.getMessageFormattedDateAsString(date)).isEqualTo("16:18, 02 juil.")
    }
}
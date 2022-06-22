package com.cyriltheandroid.leboncv.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringUtilsTest {

    @Test
    fun `getFormattedContent() with break line`() {
        val str = "Lorem ipsum dolor sit amet,\\nconsectetur adipiscing elit."
        assertThat(StringUtils.getFormattedContent(str)).isEqualTo("Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit.")
    }

    @Test
    fun `getFormattedContent() without break line`() {
        val str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        assertThat(StringUtils.getFormattedContent(str)).isEqualTo("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }

    @Test
    fun `getFormattedContent() with null string`() {
        val str: String? = null
        assertThat(StringUtils.getFormattedContent(str)).isEqualTo("")
    }
}
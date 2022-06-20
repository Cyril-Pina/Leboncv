package com.cyriltheandroid.leboncv.utils

class StringUtils {
    companion object {
        @JvmStatic
        fun getFormattedContent(content: String?) = content?.replace("\\n", "\n") ?: ""
    }
}
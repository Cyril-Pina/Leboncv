package com.cyriltheandroid.leboncv.utils

import java.text.SimpleDateFormat
import java.util.*

const val SEC_TO_HOUR: Long = 1000 * 60 * 60
const val ONE_HOUR = 1

const val TIMESTAMP_DATE_FORMAT = "dd/MM/y 'à' HH':'mm"
const val MESSAGE_DATE_FORMAT = "HH':'mm, dd MMM"

fun getDatesDifferenceInHour(date1: Date, date2: Date): Long {
    val diff: Long = date2.time - date1.time
    return diff / SEC_TO_HOUR
}

class DateUtils {
    companion object {
        @JvmStatic
        fun getTimestampFormattedDateAsString(date: Date) =
            getFormattedDate(date, TIMESTAMP_DATE_FORMAT)

        @JvmStatic
        fun getMessageFormattedDateAsString(date: Date) =
            getFormattedDate(date, MESSAGE_DATE_FORMAT)

        private fun getFormattedDate(date: Date, format: String): String {
            val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
            return simpleDateFormat.format(date)
        }
    }
}
package com.cyriltheandroid.leboncv.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
open class PPDuration(
    open val dateBegin: Date?,
    open val dateEnd: Date?
) : Parcelable {
    @IgnoredOnParcel
    private val format by lazy {
        SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    }

    open fun getReadableBeginDate(): String? = dateBegin?.let { format.format(it) }

    open fun getReadableEndingDate(): String? = dateEnd?.let { format.format(it) }

    open fun getDateDifference(): String? =
        dateBegin?.let { _dateBegin ->
            val dateEnd: Date = dateEnd ?: Date()
            val diffInMilli = dateEnd.time - _dateBegin.time
            val diffInYears = diffInMilli / (1000L * 60 * 60 * 24 * 365)
            if (diffInYears > 0) {
                "$diffInYears ans"
            } else {
                val diffInMonths = (diffInMilli / (1000 * 60 * 60 * 24) % 365) / 30
                "$diffInMonths mois"
            }
        }
}

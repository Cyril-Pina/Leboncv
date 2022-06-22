package com.cyriltheandroid.leboncv.data.model

import android.content.Context
import android.os.Parcelable
import com.cyriltheandroid.leboncv.R
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

    open fun getDateDifference(context: Context): String? =
        dateBegin?.let { _dateBegin ->
            val dateEnd: Date = dateEnd ?: Date()
            val diffInMilli = dateEnd.time - _dateBegin.time
            val diffInYears = diffInMilli / (1000L * 60 * 60 * 24 * 365)
            if (diffInYears > 0) {
                context.resources.getQuantityString(R.plurals.amount_of_years, diffInYears.toInt(), diffInYears)
            } else {
                val diffInMonths = (diffInMilli / (1000 * 60 * 60 * 24) % 365) / 30
                if (diffInMonths >= 1) {
                    context.resources.getQuantityString(R.plurals.amount_of_months, diffInMonths.toInt(), diffInMonths)
                } else {
                    context.getString(R.string.less_than_1_month)
                }
            }
        }
}

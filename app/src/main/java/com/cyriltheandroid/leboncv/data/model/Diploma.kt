package com.cyriltheandroid.leboncv.data.model

import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Diploma(
    val title: String?,
    val promotion: Int?,
    val school: String?,
    override val dateBegin: Date?,
    override val dateEnd: Date?
) : PPDuration(dateBegin, dateEnd)

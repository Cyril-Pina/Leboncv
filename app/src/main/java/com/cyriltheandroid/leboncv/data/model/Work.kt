package com.cyriltheandroid.leboncv.data.model

import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Work(
    val position: String?,
    val companyName: String?,
    val contractType: ContractType?,
    override val dateBegin: Date?,
    override val dateEnd: Date?
) : PPDuration(dateBegin, dateEnd)
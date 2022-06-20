package com.cyriltheandroid.leboncv.adapter

import javax.inject.Inject

open class ItemClickListener @Inject constructor() {

    var onItemClick: ((Int, Any?) -> Unit)? = null

    fun onClick(idx: Int, data: Any?) {
        onItemClick?.invoke(idx, data)
    }
}

package com.cyriltheandroid.leboncv.ui.adapter

import javax.inject.Inject

class CopyClipboardClickListener @Inject constructor() : ItemClickListener() {

    var onCopyButtonClick: ((Int, Any?) -> Unit)? = null

    fun onCopy(idx: Int, data: Any?) {
        onCopyButtonClick?.invoke(idx, data)
    }
}

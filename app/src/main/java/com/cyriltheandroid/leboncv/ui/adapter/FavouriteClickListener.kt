package com.cyriltheandroid.leboncv.ui.adapter

import android.view.View
import com.cyriltheandroid.leboncv.data.model.Article
import javax.inject.Inject

class FavouriteClickListener @Inject constructor() {

    var onItemClick: ((Int, View, Article) -> Unit)? = null

    fun onClick(idx: Int, animationView: View, article: Article) {
        onItemClick?.invoke(idx, animationView, article)
    }
}

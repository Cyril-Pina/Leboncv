package com.cyriltheandroid.leboncv.adapter

import android.view.View
import com.cyriltheandroid.leboncv.model.Article
import javax.inject.Inject

class FavouriteClickListener @Inject constructor() {

    var onItemClick: ((Int, View, Article) -> Unit)? = null

    fun onClick(idx: Int, animationView: View, article: Article) {
        onItemClick?.invoke(idx, animationView, article)
    }
}

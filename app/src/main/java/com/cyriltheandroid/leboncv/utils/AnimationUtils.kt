package com.cyriltheandroid.leboncv.utils

import com.airbnb.lottie.LottieAnimationView

fun startFavouriteAnimationView(isFav: Boolean, animationView: LottieAnimationView) {
    val minFrame = if (!isFav) MIN_FRAME_ADD_FAV else MIN_FRAME_REMOVE_FAV
    val maxFrame = if (!isFav) MAX_FRAME_ADD_FAV else MAX_FRAME_REMOVE_FAV

    animationView.startAnimation(minFrame, maxFrame)
}
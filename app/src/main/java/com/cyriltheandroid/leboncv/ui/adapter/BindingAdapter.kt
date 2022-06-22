package com.cyriltheandroid.leboncv.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.cyriltheandroid.leboncv.R

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun bindImageViewSource(imageView: ImageView, idRes: Int) {
            imageView.setImageResource(idRes)
        }

        @JvmStatic
        @BindingAdapter("app:srcCompat")
        fun bindImageCompatViewSource(imageView: ImageView, idRes: Int) {
            imageView.setImageResource(idRes)
        }

        @JvmStatic
        @BindingAdapter("lottie_progress")
        fun bindAnimationViewProgress(animationView: LottieAnimationView, progress: Float) {
            animationView.progress = progress
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun bindImageFromUrl(imageView: ImageView, imageUrl: String?) {
            Glide
                .with(imageView.context)
                .load(imageUrl)
                .placeholder(android.R.color.darker_gray)
                .error(R.drawable.img_broke)
                .into(imageView)
        }
    }
}
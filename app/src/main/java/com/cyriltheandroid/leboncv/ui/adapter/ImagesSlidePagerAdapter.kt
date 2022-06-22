package com.cyriltheandroid.leboncv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemImageBinding
import com.cyriltheandroid.leboncv.data.model.ArticleImage
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ImagesSlidePagerAdapter @Inject constructor() :
    RecyclerView.Adapter<ImagesSlidePagerAdapter.ViewHolder>() {

    lateinit var images: List<ArticleImage>

    class ViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(articleImage: ArticleImage) {
            binding.imageUrl = articleImage.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

}
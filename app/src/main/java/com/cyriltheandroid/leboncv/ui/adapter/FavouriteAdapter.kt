package com.cyriltheandroid.leboncv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemFavouriteBinding
import com.cyriltheandroid.leboncv.data.model.Article
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FavouriteAdapter @Inject constructor(
    val clickListener: ItemClickListener,
    val favClickListener: FavouriteClickListener
) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    var articles: List<Article> = emptyList()

    class ViewHolder(val binding: ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            idx: Int,
            article: Article,
            clickListener: ItemClickListener,
            favClickListener: FavouriteClickListener
        ) {
            binding.index = idx
            binding.article = article
            binding.clickListener = clickListener
            binding.favClickListener = favClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFavouriteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, articles[position], clickListener, favClickListener)
    }

    override fun getItemCount(): Int = articles.size
}
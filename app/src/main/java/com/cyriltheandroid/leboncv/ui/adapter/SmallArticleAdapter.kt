package com.cyriltheandroid.leboncv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemSmallArticleBinding
import com.cyriltheandroid.leboncv.data.model.Article
import javax.inject.Inject

class SmallArticleAdapter @Inject constructor(
    val clickListener: ItemClickListener,
    val favClickListener: FavouriteClickListener
) :
    RecyclerView.Adapter<SmallArticleAdapter.ViewHolder>() {

    var articles: List<Article> = emptyList()

    class ViewHolder(val binding: ItemSmallArticleBinding) : RecyclerView.ViewHolder(binding.root) {
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
        val binding = ItemSmallArticleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, articles[position], clickListener, favClickListener)
    }

    override fun getItemCount(): Int = articles.size
}
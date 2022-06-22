package com.cyriltheandroid.leboncv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemCategoryHistoryBinding
import com.cyriltheandroid.leboncv.data.model.Article
import com.cyriltheandroid.leboncv.data.model.Category
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class CategoryHistoryAdapter @Inject constructor(
    val categoryClickListener: ItemClickListener,
    val articlesClickListener: ItemClickListener,
    val favClickListener: FavouriteClickListener
) :
    RecyclerView.Adapter<CategoryHistoryAdapter.ViewHolder>() {

    var categories: List<Category> = emptyList()
    var allArticles: List<List<Article>> = emptyList()

    class ViewHolder(val binding: ItemCategoryHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            idx: Int,
            category: Category,
            clickListener: ItemClickListener,
            smallArticleAdapter: SmallArticleAdapter
        ) {
            binding.index = idx
            binding.category = category
            binding.clickListener = clickListener
            binding.smallArticlesRecylerView.adapter = smallArticleAdapter
            binding.notifyChange()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryHistoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val smallArticleAdapter = SmallArticleAdapter(ItemClickListener(), FavouriteClickListener())
        smallArticleAdapter.articles = allArticles[position]
        smallArticleAdapter.clickListener.onItemClick = { idx, obj ->
            articlesClickListener.onClick(idx, obj)
        }
        smallArticleAdapter.favClickListener.onItemClick = { idx, view, obj ->
            favClickListener.onClick(idx, view, obj)
        }
        holder.bind(
            position,
            categories[position],
            categoryClickListener,
            smallArticleAdapter
        )
    }

    override fun getItemCount() = allArticles.size
}
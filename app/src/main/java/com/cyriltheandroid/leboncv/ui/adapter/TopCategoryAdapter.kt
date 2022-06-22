package com.cyriltheandroid.leboncv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemTopCategoryBinding
import com.cyriltheandroid.leboncv.data.model.Category
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TopCategoryAdapter @Inject constructor(val clickListener: ItemClickListener) :
    RecyclerView.Adapter<TopCategoryAdapter.ViewHolder>() {

    var topCategories: List<Category> = emptyList()

    class ViewHolder(val binding: ItemTopCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(idx: Int, topCategory: Category, clickListener: ItemClickListener) {
            binding.index = idx
            binding.topCategory = topCategory
            binding.clickListener = clickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopCategoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, topCategories[position], clickListener)
    }

    override fun getItemCount(): Int = topCategories.size
}
package com.cyriltheandroid.leboncv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemProfileMenuBinding
import com.cyriltheandroid.leboncv.data.model.ItemMenu
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ProfileMenuAdapter @Inject constructor(val clickListener: ItemClickListener) :
    RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>() {

    var itemsMenu: List<ItemMenu> = emptyList()

    class ViewHolder(val binding: ItemProfileMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(idx: Int, item: ItemMenu, clickListener: ItemClickListener) {
            binding.index = idx
            binding.itemMenu = item
            binding.clickListener = clickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileMenuBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, itemsMenu[position], clickListener)
    }

    override fun getItemCount(): Int = itemsMenu.size

}
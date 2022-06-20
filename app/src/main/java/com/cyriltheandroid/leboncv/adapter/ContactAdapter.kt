package com.cyriltheandroid.leboncv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemContactBinding
import com.cyriltheandroid.leboncv.model.ItemContact
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ContactAdapter @Inject constructor(val clickListener: CopyClipboardClickListener) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var contactList: List<ItemContact> = emptyList()

    class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(idx: Int, contact: ItemContact, clickListener: CopyClipboardClickListener) {
            binding.index = idx
            binding.contact = contact
            binding.clickListener = clickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, contactList[position], clickListener)
    }

    override fun getItemCount(): Int = contactList.size
}
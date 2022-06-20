package com.cyriltheandroid.leboncv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemLangBinding
import com.cyriltheandroid.leboncv.model.Language
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class LanguageAdapter @Inject constructor() :
    RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    var languages: List<Language> = emptyList()

    class ViewHolder(val binding: ItemLangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {
            binding.language = language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLangBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(languages[position])
    }

    override fun getItemCount(): Int = languages.size
}
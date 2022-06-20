package com.cyriltheandroid.leboncv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemSkillBinding
import com.cyriltheandroid.leboncv.model.Skill
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class SkillAdapter : RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    var skills: List<Skill> = emptyList()

    class ViewHolder(val binding: ItemSkillBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(skill: Skill) {
            binding.skill = skill
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSkillBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(skills[position])
    }

    override fun getItemCount(): Int = skills.size
}
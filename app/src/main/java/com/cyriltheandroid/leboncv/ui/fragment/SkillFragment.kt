package com.cyriltheandroid.leboncv.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cyriltheandroid.leboncv.ui.adapter.SkillAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentSkillBinding
import com.cyriltheandroid.leboncv.data.model.Skill
import dagger.hilt.android.AndroidEntryPoint

const val SKILLS_LIST_KEY = "skills_list"

@AndroidEntryPoint
class SkillFragment : Fragment() {

    companion object {
        fun newInstance(skills: List<Skill>) = SkillFragment().apply {
            this.skills = skills
        }
    }

    private lateinit var binding: FragmentSkillBinding
    private var skills: List<Skill> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSkillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            fetchSkillsFromSavedInstanceState(it)
        }
        initSkillRecyclerView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SKILLS_LIST_KEY, skills as ArrayList<Skill>)
    }

    private fun fetchSkillsFromSavedInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey(SKILLS_LIST_KEY)) {
            skills = savedInstanceState.getParcelableArrayList(SKILLS_LIST_KEY)!!
        }
    }

    private fun initSkillRecyclerView() {
        val skillAdapter = SkillAdapter()
        skillAdapter.skills = skills
        binding.skillsRecyclerView.adapter = skillAdapter
    }
}
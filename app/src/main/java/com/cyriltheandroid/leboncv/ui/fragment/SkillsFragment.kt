package com.cyriltheandroid.leboncv.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.databinding.FragmentSkillsBinding
import com.cyriltheandroid.leboncv.data.model.Skill
import com.cyriltheandroid.leboncv.ui.viewmodel.SkillsViewModel
import com.cyriltheandroid.leboncv.utils.setCloseFragmentOnClick
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkillsFragment : Fragment() {

    private val skillTypes by lazy {
        arrayOf(
            getString(R.string.hard_skills_tab_title),
            getString(R.string.soft_skills_tab_title),
        )
    }

    private val skillsViewModel: SkillsViewModel by viewModels()
    private lateinit var binding: FragmentSkillsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSkillsBinding.inflate(inflater, container, false)
        setCloseFragmentOnClick(binding.backArrowImageButton)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        skillsViewModel.allSkills.observe(viewLifecycleOwner) {
            initSkillsViewPager(it)
        }
    }

    private fun initSkillsViewPager(allSkills: List<List<Skill>>) {
        val skillsAdapter = SkillsSlidePagerAdapter(requireActivity(), allSkills)
        binding.skillsViewPager.adapter = skillsAdapter

        TabLayoutMediator(binding.skillsTabLayout, binding.skillsViewPager) { tab, position ->
            tab.text = skillTypes[position]
        }.attach()
    }

    private inner class SkillsSlidePagerAdapter(
        fa: FragmentActivity,
        val allSkills: List<List<Skill>>
    ) : FragmentStateAdapter(fa) {

        override fun getItemCount() = allSkills.size

        override fun createFragment(position: Int) = SkillFragment.newInstance(allSkills[position])
    }
}
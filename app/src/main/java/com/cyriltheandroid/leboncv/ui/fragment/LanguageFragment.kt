package com.cyriltheandroid.leboncv.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cyriltheandroid.leboncv.adapter.LanguageAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentLangBinding
import com.cyriltheandroid.leboncv.ui.viewmodel.LanguageViewModel
import com.cyriltheandroid.leboncv.utils.setCloseFragmentOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LanguageFragment : Fragment() {

    private val languageViewModel: LanguageViewModel by viewModels()
    private lateinit var binding: FragmentLangBinding

    @Inject
    lateinit var languageAdapter: LanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLangBinding.inflate(inflater, container, false)
        setCloseFragmentOnClick(binding.backArrowImageButton)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLangRecyclerView()
    }

    private fun initLangRecyclerView() {
        languageAdapter.languages = languageViewModel.languages
        binding.languagesRecyclerView.adapter = languageAdapter
    }
}
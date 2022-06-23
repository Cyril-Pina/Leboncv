package com.cyriltheandroid.leboncv.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cyriltheandroid.leboncv.databinding.FragmentProfileDetailsBinding
import com.cyriltheandroid.leboncv.ui.viewmodel.ProfileDetailsViewModel
import com.cyriltheandroid.leboncv.utils.resumePdfUrl
import com.cyriltheandroid.leboncv.utils.setCloseFragmentOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProfileDetailsBinding
    private val profileDetailsViewModel: ProfileDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = profileDetailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initClickListeners()
        setCloseFragmentOnClick(binding.backArrowImageButton)
        return binding.root
    }

    private fun initClickListeners() {
        binding.showResumeButton.setOnClickListener {
            showResumeInWebBrowser()
        }
    }

    private fun showResumeInWebBrowser() {
        val resumeUrlToLoad = "https://docs.google.com/gview?embedded=true&url=$resumePdfUrl"
        val uri = Uri.parse(resumeUrlToLoad)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        requireContext().startActivity(intent)
    }
}
package com.cyriltheandroid.leboncv.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cyriltheandroid.leboncv.databinding.FragmentProfileDetailsBinding
import com.cyriltheandroid.leboncv.ui.viewmodel.ProfileDetailsViewModel
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
            navigateToResumeActivity()
        }
    }

    private fun navigateToResumeActivity() {
        val action = ProfileDetailsFragmentDirections.actionProfileDetailsFragmentToResumeActivity()
        val navController = findNavController()
        navController.navigate(action)
    }
}
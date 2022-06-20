package com.cyriltheandroid.leboncv.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.adapter.ProfileMenuAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentProfileBinding
import com.cyriltheandroid.leboncv.model.Profile
import com.cyriltheandroid.leboncv.ui.viewmodel.ProfileViewModel
import com.cyriltheandroid.leboncv.ui.viewmodel.SharedViewModel
import com.cyriltheandroid.leboncv.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var profileMenuAdapter: ProfileMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = profileViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initMenuItemsRecyclerView()
    }

    private fun initClickListeners() {
        binding.profileCardView.setOnClickListener { _ ->
            profileViewModel.profile.value?.let { navigateToProfileDetails(it) }
        }
    }

    private fun initMenuItemsRecyclerView() {
        profileMenuAdapter.itemsMenu = profileViewModel.menuItems
        profileMenuAdapter.clickListener.onItemClick = { idx, _ ->
            redirectOnItemMenuClicked(idx)
        }
        binding.profileMenuRecyclerView.addDivider()
        binding.profileMenuRecyclerView.adapter = profileMenuAdapter
    }

    private fun redirectOnItemMenuClicked(idx: Int) {
        when (idx) {
            FAV_INDEX -> sharedViewModel.triggerFavouriteTab()
            SKILLS_INDEX -> {
                navigateToFragmentFromMenu(
                    ProfileFragmentDirections.actionProfileFragmentToSkillsFragment()
                )
            }
            LANG_INDEX -> navigateToFragmentFromMenu(ProfileFragmentDirections.actionProfileFragmentToLangFragment())
            CONTACT_INDEX -> navigateToFragmentFromMenu(ProfileFragmentDirections.actionProfileFragmentToContactFragment())
            ABOUT_INDEX -> showAboutAlertDialog()
        }
    }

    private fun navigateToFragmentFromMenu(action: NavDirections) {
        val navController = findNavController()
        navController.navigate(action)
    }

    private fun showAboutAlertDialog() {
        val builder = AlertDialog.Builder(context)
            .setTitle(R.string.about_title_a_d)
            .setMessage(R.string.about_msg_a_d)
            .setPositiveButton(R.string.close) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun navigateToProfileDetails(profile: Profile) {
        val action = ProfileFragmentDirections
            .actionProfileFragmentToProfileDetailsFragment(profile)
        val navController = findNavController()
        navController.navigate(action)
    }
}
package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.mapper.ProfileMapper
import com.cyriltheandroid.leboncv.model.Contact
import com.cyriltheandroid.leboncv.model.ItemContact
import com.cyriltheandroid.leboncv.model.ItemMenu
import com.cyriltheandroid.leboncv.model.Profile
import com.cyriltheandroid.leboncv.repository.ProfileRepository
import com.cyriltheandroid.leboncv.utils.getItemsContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    profileRepository: ProfileRepository,
    private val profileMapper: ProfileMapper,
) : ViewModel() {

    val profile: LiveData<Profile> = profileRepository.getUserProfile().map {
        profileMapper.map(it)
    }.asLiveData()

    val itemsContact: LiveData<List<ItemContact>> = profileRepository.getUserProfile().map {
        val profile = it.profileEntity
        Contact(profile.phoneNumber, profile.countryPrefix, profile.emailAddress).getItemsContact()
    }.asLiveData()

    val menuItems = listOf(
        ItemMenu(R.string.fav_item_menu, R.drawable.ic_fav),
        ItemMenu(R.string.skills_item_menu, R.drawable.ic_skills),
        ItemMenu(R.string.lang_item_menu, R.drawable.ic_lang),
        ItemMenu(R.string.contact_item_menu, R.drawable.ic_contact),
        ItemMenu(R.string.about_item_menu, R.drawable.ic_info),
    )

}
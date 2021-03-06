package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cyriltheandroid.leboncv.data.mapper.ProfileMapper
import com.cyriltheandroid.leboncv.data.model.Profile
import com.cyriltheandroid.leboncv.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProfileDetailsViewModel @Inject constructor(
    profileRepository: ProfileRepository,
    private val profileMapper: ProfileMapper
) : ViewModel() {
    val profile: LiveData<Profile> = profileRepository.getUserProfile().map {
        profileMapper.map(it)
    }.asLiveData()
}
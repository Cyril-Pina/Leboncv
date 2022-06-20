package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val openFavouriteLiveData = MutableLiveData<Int>()
    val favourite: LiveData<Int> = openFavouriteLiveData

    fun triggerFavouriteTab() {
        openFavouriteLiveData.value = 0
    }
}
package com.cyriltheandroid.leboncv.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
class Location(
    val streetAddress: String?,
    val city: String?,
    val zipCode: String?,
    val country: String?,
    val latLng: LatLng?
) : Parcelable {
    fun getCityWithZipCode() = "$city $zipCode"
}
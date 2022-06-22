package com.cyriltheandroid.leboncv.data.mapper

import com.cyriltheandroid.leboncv.data.model.Contact
import com.cyriltheandroid.leboncv.data.model.Location
import com.cyriltheandroid.leboncv.data.model.Profile
import com.cyriltheandroid.leboncv.room.entity.ProfilePOJO
import com.cyriltheandroid.leboncv.room.entity.ProfileEntity
import com.cyriltheandroid.leboncv.room.entity.UserLocationEntity
import com.cyriltheandroid.leboncv.utils.LAT_INDEX
import com.cyriltheandroid.leboncv.utils.LNG_INDEX
import com.google.android.gms.maps.model.LatLng
import java.lang.NumberFormatException

class ProfileMapper : DataMapper<Profile, ProfilePOJO> {
    override fun map(obj: ProfilePOJO): Profile {
        val location = locationEntityToLocation(obj.location)
        val contact = getContactFromProfileEntity(obj.profileEntity)
        return Profile(
            obj.profileEntity.firstName,
            obj.profileEntity.lastName,
            obj.profileEntity.age,
            obj.profileEntity.pictureResId,
            obj.profileEntity.jobDescription,
            obj.profileEntity.lifeResume,
            location,
            contact
        )
    }

    private fun locationEntityToLocation(locationEntity: UserLocationEntity): Location? {
        val latLngArray = locationEntity.latLng?.split(",")
        return try {
            val lat = latLngArray?.get(LAT_INDEX)?.toDouble()
            val lng = latLngArray?.get(LNG_INDEX)?.toDouble()
            Location(
                locationEntity.streetAddress,
                locationEntity.city,
                locationEntity.zipCode,
                locationEntity.country,
                if (lat != null && lng != null) LatLng(lat, lng) else null
            )
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
            null
        }
    }

    private fun getContactFromProfileEntity(profileEntity: ProfileEntity): Contact = Contact(
        profileEntity.phoneNumber,
        profileEntity.countryPrefix,
        profileEntity.emailAddress
    )
}
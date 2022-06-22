package com.cyriltheandroid.leboncv.data.mapper

import androidx.room.Embedded
import androidx.room.Relation
import com.cyriltheandroid.leboncv.room.entity.ProfileEntity
import com.cyriltheandroid.leboncv.room.entity.ProfilePOJO
import com.cyriltheandroid.leboncv.room.entity.UserLocationEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ProfileMapperTest {

    lateinit var profileMapper: ProfileMapper

    lateinit var profileEntity: ProfileEntity
    lateinit var location: UserLocationEntity

    @Before
    fun setUp() {
        profileMapper = ProfileMapper()
        profileEntity = ProfileEntity(1, "firstName", "lastName", 1, 1, "pictureUrl", "jobDesc"
        , "lifeResume", "062743", 33, "emailAddress")
        location = UserLocationEntity(1, "address", "city", "75", "country", "4.49539,49584", 1)
    }

    @Test
    fun mapTest() {
        val profilePOJO = ProfilePOJO(profileEntity, location)
        val mappedProfile = profileMapper.map(profilePOJO)

        assertThat(mappedProfile.getFullName()).isEqualTo("firstName lastName")
        assertThat(mappedProfile.contact?.getFullNumberFormat()).isEqualTo("+33 62743")
        assertThat(mappedProfile.location?.getCityWithZipCode()).isEqualTo("city 75")
    }
}
package com.cyriltheandroid.leboncv

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cyriltheandroid.leboncv.ui.fragment.CategoryDetailsFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDetailsFragmentTest {

    @Test
    fun writeInSearchEditTextTest() {
        launchFragmentInHiltContainer<CategoryDetailsFragment>()

        onView(withId(R.id.search_edit_text)).perform(typeText("Hobbies"))

        onView(withId(R.id.search_edit_text)).check(matches(withText("Hobbies")))
    }
}

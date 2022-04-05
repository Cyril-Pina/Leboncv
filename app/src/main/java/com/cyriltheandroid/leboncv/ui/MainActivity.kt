package com.cyriltheandroid.leboncv.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.databinding.ActivityMainBinding
import com.cyriltheandroid.leboncv.utils.FAV_FRAG
import com.cyriltheandroid.leboncv.utils.MSG_FRAG
import com.cyriltheandroid.leboncv.utils.PROFILE_FRAG
import com.cyriltheandroid.leboncv.utils.SEARCH_FRAG
import com.google.android.material.navigation.NavigationBarView

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val searchFrag = SearchFragment()
    private val favFrag = FavouriteFragment()
    private val msgFrag = MessageFragment()
    private val profileFrag = ProfileFragment()

    private lateinit var showedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavMenu()
    }

    private fun initBottomNavMenu() {
        addFragmentsInFragmentManager()
        val bottomNavItemClickListener = getOnBottomNavItemClickListener()
        binding.bottomNavigation.setOnItemSelectedListener(bottomNavItemClickListener)
    }

    private fun addFragmentsInFragmentManager() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            add(binding.mainFrameLayout.id, searchFrag, SEARCH_FRAG)
            add(binding.mainFrameLayout.id, favFrag, FAV_FRAG)
            add(binding.mainFrameLayout.id, msgFrag, MSG_FRAG)
            add(binding.mainFrameLayout.id, profileFrag, PROFILE_FRAG)
            hide(favFrag)
            hide(msgFrag)
            hide(profileFrag)
            commit()
        }
        showedFragment = searchFrag
    }

    private fun getOnBottomNavItemClickListener(): NavigationBarView.OnItemSelectedListener {
        return NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search_page -> {
                    updateCurrentFragment(searchFrag)
                    true
                }
                R.id.favourite_page -> {
                    updateCurrentFragment(favFrag)
                    true
                }
                R.id.message_page -> {
                    updateCurrentFragment(msgFrag)
                    true
                }
                R.id.profile_page -> {
                    updateCurrentFragment(profileFrag)
                    true
                }
                else -> false
            }
        }
    }

    private fun updateCurrentFragment(newFragment: Fragment) {
        if (newFragment.isVisible) return
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            hide(showedFragment)
            show(newFragment)
            commit()
        }
        showedFragment = newFragment
    }
}
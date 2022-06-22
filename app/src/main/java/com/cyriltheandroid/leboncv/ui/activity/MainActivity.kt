package com.cyriltheandroid.leboncv.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.databinding.ActivityMainBinding
import com.cyriltheandroid.leboncv.ui.viewmodel.SharedViewModel
import com.cyriltheandroid.leboncv.utils.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

private const val TAB_BAR_ITEM_ID_KEY = "tab_bar_item_id"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedViewModel: SharedViewModel by viewModels()
    private var lastSelectedTabItemId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationMenu()
        initObservers()
        savedInstanceState?.let {
            lastSelectedTabItemId = savedInstanceState.getInt(TAB_BAR_ITEM_ID_KEY)
            binding.bottomNavigation.selectedItemId = lastSelectedTabItemId ?: R.id.search_page
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAB_BAR_ITEM_ID_KEY, binding.bottomNavigation.selectedItemId)
    }

    override fun onBackPressed() {
        if (findNavController(R.id.activity_main_nav_host_fragment).popBackStack().not()) {
            finish()
        }
    }

    private fun initObservers() {
        sharedViewModel.favourite.observe(this) {
            binding.bottomNavigation.selectedItemId = lastSelectedTabItemId ?: R.id.favourite_page
            lastSelectedTabItemId = null
        }
    }

    private fun initBottomNavigationMenu() {
        val navGraphIds = getNavGraphTabs()

        binding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.activity_main_nav_host_fragment,
            intent = intent
        )
    }

    private fun getNavGraphTabs() = listOf(
        R.navigation.nav_search_page,
        R.navigation.nav_favourite_page,
        R.navigation.nav_message_page,
        R.navigation.nav_profile_page
    )
}
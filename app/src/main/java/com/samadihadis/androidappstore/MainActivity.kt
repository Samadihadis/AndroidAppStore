package com.samadihadis.androidappstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.samadihadis.androidappstore.databinding.ActivityMainBinding
import com.samadihadis.androidappstore.util.SharePreferencesManager
import com.samadihadis.androidappstore.util.SharePreferencesManager.Companion.IS_DARK_MODE_ENABLE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storage by lazy {
        SharePreferencesManager(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView : BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.navMainFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.appHomeFragment , R.id.appHomeFragment , R.id.accountFragment
            )
        )
        setupActionBarWithNavController(navController , appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        supportActionBar!!.hide()

        val isDarkModeEnabled = storage.retrieveBoolean(IS_DARK_MODE_ENABLE)

        AppCompatDelegate.setDefaultNightMode(
            if (isDarkModeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )
    }
}
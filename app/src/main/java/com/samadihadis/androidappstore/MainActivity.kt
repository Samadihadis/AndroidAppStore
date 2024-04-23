package com.samadihadis.androidappstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.samadihadis.androidappstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView : BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.navMainFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.appsFragment , R.id.appsFragment , R.id.accountFragment
            )
        )
        setupActionBarWithNavController(navController , appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
}
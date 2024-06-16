package com.samadihadis.androidappstore.peresentaion.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.samadihadis.androidappstore.databinding.FragmentAccountBinding
import com.samadihadis.androidappstore.util.SharePreferencesManager

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    private val storage by lazy {
        SharePreferencesManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isDarkModeEnabled = storage.retrieveTheme("isDarkModeEnabled", false)
        binding.themeSwitch.isChecked = isDarkModeEnabled

        binding.themeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                storage.saveTheme("isDarkModeEnabled" , true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                storage.saveTheme("isDarkModeEnabled" , false)
            }
        }
    }

}
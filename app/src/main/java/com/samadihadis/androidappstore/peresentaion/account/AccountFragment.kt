package com.samadihadis.androidappstore.peresentaion.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.samadihadis.androidappstore.databinding.FragmentAccountBinding
import com.samadihadis.androidappstore.util.SharePreferencesManager
import com.samadihadis.androidappstore.util.SharePreferencesManager.Companion.IS_DARK_MODE_ENABLE

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
        changeThemeSettings()
    }

    private fun changeThemeSettings() = binding.darkThemeSettings.themeSwitch.apply {
        isChecked = storage.retrieveBoolean(IS_DARK_MODE_ENABLE)
        setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                storage.saveBoolean(IS_DARK_MODE_ENABLE , true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                storage.saveBoolean(IS_DARK_MODE_ENABLE , false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
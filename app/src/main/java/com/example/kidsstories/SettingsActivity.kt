package com.example.kidsstories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kidsstories.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private val SETTINGS = "settings"
    private val TEXT_SIZE = "size"
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.settings)

        val viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        val radioGroup = binding.textSizeRadiogroup
        val pref = getSharedPreferences(SETTINGS, MODE_PRIVATE)

        when (pref.getFloat(TEXT_SIZE, 24F)) {
            20F -> radioGroup.check(R.id.small_rb)
            28F -> radioGroup.check(R.id.large_rb)
        }

        viewModel.textSize.observe(this) {
            binding.smallRb.textSize = it
            binding.mediumRb.textSize = it
            binding.largeRb.textSize = it
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.small_rb -> viewModel.textSize.value = 20F
                R.id.medium_rb -> viewModel.textSize.value = 24F
                R.id.large_rb -> viewModel.textSize.value = 28F
            }
        }
    }

    private fun saveTextSize(size: Float) {
        val prefEdit = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit()
        prefEdit.putFloat(TEXT_SIZE, size)
        prefEdit.apply()
    }

    override fun onPause() {
        super.onPause()
        saveTextSize(
            when (binding.textSizeRadiogroup.checkedRadioButtonId) {
                R.id.small_rb -> 20F
                R.id.large_rb -> 28F
                else -> 24F
            }
        )
    }
}
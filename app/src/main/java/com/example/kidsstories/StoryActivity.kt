package com.example.kidsstories

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.kidsstories.databinding.ActivityStoryBinding

class StoryActivity : AppCompatActivity() {
    private val OBJECT_NAME = "com.example.kidsstories.ANIMAL"
    private var _binding: ActivityStoryBinding? = null
    private val binding get() = _binding!!
    private var visibilitySwitch = false
        get() {
            field = !field
            return field
        }
    private lateinit var media: MediaPlayer
    private val SETTINGS = "settings"
    private val TEXT_SIZE = "size"
    private lateinit var textPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.story)
        val actionBar = supportActionBar

        val animal: Animal? = intent.getParcelableExtra(OBJECT_NAME)

        animal?.let {
            actionBar?.subtitle = it.title
            setViews(it)
        }

        textPref = getSharedPreferences(SETTINGS, MODE_PRIVATE)

        binding.storyImage.setOnClickListener {
            binding.storyText.visibility = if (visibilitySwitch) View.GONE else View.VISIBLE
        }

        binding.storyText.setOnClickListener {
            binding.storyImage.visibility = if (visibilitySwitch) View.GONE else View.VISIBLE
        }
    }

    private fun setViews(animal: Animal) {
        binding.storyText.text = getString(animal.story)
        binding.storyImage.setImageResource(animal.picture)
        media = MediaPlayer.create(this, animal.sound)
        media.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, SettingsActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        val textSize = textPref.getFloat(TEXT_SIZE, 24F)
        binding.storyText.textSize = textSize
    }

    override fun onPause() {
        super.onPause()
        if (this::media.isInitialized)
            media.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
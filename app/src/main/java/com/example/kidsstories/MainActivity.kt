package com.example.kidsstories

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kidsstories.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val OBJECT_NAME = "com.example.kidsstories.ANIMAL"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.main_name)
        val animals = arrayListOf(
            Animal(
                getString(R.string.bee_title),
                R.raw.bee,
                R.drawable.bee,
                R.string.bee_story
            ),
            Animal(
                getString(R.string.chicken_title),
                R.raw.chicken,
                R.drawable.chicken,
                R.string.chicken_story
            ),
            Animal(
                getString(R.string.cat_title),
                R.raw.cat,
                R.drawable.cat,
                R.string.cat_story
            ),
            Animal(
                getString(R.string.duck_title),
                R.raw.duck,
                R.drawable.duck,
                R.string.duck_story
            )
        )

        val listener = AnimalListAdapter.AnimalItemCL { _, position ->
            val intent = Intent(this, StoryActivity::class.java)
            intent.putExtra(OBJECT_NAME, animals[position])
            startActivity(intent)
        }

        val adapter = AnimalListAdapter(animals, listener)
        binding.animalRv.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Temporary, because we have one item.
        startActivity(Intent(this,SettingsActivity::class.java))
        return super.onOptionsItemSelected(item)
    }


}
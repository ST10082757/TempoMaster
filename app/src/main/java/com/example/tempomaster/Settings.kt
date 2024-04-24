package com.example.tempomaster

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.example.tempomaster.databinding.ActivitySettingsBinding

class Settings : AppCompatActivity() {
    // Declaration of the binding variable at the class level
    private lateinit var binding: ActivitySettingsBinding

    // Define the fragments to be used in your activity
    private lateinit var dashboard: Fragment
    private lateinit var existingProjects: Fragment
    private lateinit var settings: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default fragment to be displayed when activity is created
        replaceFragment(settings)

        // Enable edge-to-edge design
        enableEdgeToEdge()

        // Window insets listener for setting padding based on system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        // BottomNavigationView item selection listener
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboardID -> replaceFragment(dashboard)
                R.id.projectID -> replaceFragment(existingProjects)
                R.id.settingsID -> replaceFragment(settings)
                else -> false
            }
            true
        }
    }

    // Fragment replacement method
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null) // Optional, for back navigation
        fragmentTransaction.commit()
    }
}

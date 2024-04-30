package com.example.tempomaster

import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tempomaster.databinding.ActivityWelcomeBinding


class Welcome : AppCompatActivity() , View.OnClickListener {
    //creating project object
    var project = ProjectCategory()
    override fun onCreate(savedInstanceState: Bundle?) {
        //setting the layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //binding the welcome page with the welcome screen
        val binding = ActivityWelcomeBinding.inflate(layoutInflater)

        //adding the functionality when user clicks something
        binding.schoolbtn.setOnClickListener(this)
        binding.workbtn.setOnClickListener(this)
        binding.generalbtn.setOnClickListener(this)

        // Set click listener for Next button
        binding.btnNext.setOnClickListener {
            if (project.projectCategory != null) {
                // starting the Dashboard activity with the selected category
                val intent = Intent(this, Dashboard::class.java).apply {
                    putExtra("CATEGORY", project.projectCategory)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onClick(v: View?) {
        // Handle click events for the buttons
        when (v?.id) {
                R.id.btnwork -> project.projectCategory = "Work"
                R.id.btnschool -> project.projectCategory = "School"
                R.id.btngeneral -> project.projectCategory = "General"
            }
    }
}

package com.example.tempomaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tempomaster.com.example.tempomaster.ProjectCategory
import com.example.tempomaster.com.example.tempomaster.openIntent
import com.example.tempomaster.databinding.ActivityWelcomeBinding


class Welcome : AppCompatActivity() , View.OnClickListener {
    //creating project object
    var project = ProjectCategory()
    override fun onCreate(savedInstanceState: Bundle?) {
        //setting the layout
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //binding the welcome page with the welcome screen
        val binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adding click listeners to the buttons
        binding.workbtn.setOnClickListener(this)
        binding.schoolbtn.setOnClickListener(this)
        binding.generalbtn.setOnClickListener(this)

        val nextBtn = findViewById<Button>(R.id.btnNext)
        nextBtn.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        // Handle click events for the buttons
        when (v?.id) {
            R.id.workbtn -> {
                project.projectCategory = "Work"
                Toast.makeText(this@Welcome, "You have selected: Work", Toast.LENGTH_SHORT).show()
            }
            R.id.schoolbtn -> {
                project.projectCategory = "School"
                Toast.makeText(this@Welcome, "You have selected: School", Toast.LENGTH_SHORT).show()
            }
            R.id.generalbtn -> {
                project.projectCategory = "General"
                Toast.makeText(this@Welcome, "You have selected: General", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
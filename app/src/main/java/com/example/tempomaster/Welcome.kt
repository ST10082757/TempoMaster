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

        setContentView(R.layout.activity_welcome)

        val nextBtn = findViewById<Button>(R.id.btnNext)
        nextBtn.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        //adding the functionality when user clicks something
        binding.schoolbtn.setOnClickListener(this)
        binding.workbtn.setOnClickListener(this)
        binding.generalbtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        // Handle click events for the buttons
        when (v?.id) {
            R.id.btnwork -> project.projectCategory = "Work"
            R.id.btnschool -> project.projectCategory = "School"
            R.id.btngeneral -> project.projectCategory = "General"
        }
        Toast.makeText(this@Welcome, "You have selected :  " + project.projectCategory, Toast.LENGTH_SHORT).show()
        openIntent(this, project.projectName, Dashboard::class.java)
    }
}

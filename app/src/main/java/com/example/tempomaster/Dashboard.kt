package com.example.tempomaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tempomaster.com.example.tempomaster.ProjectCategory
import com.example.tempomaster.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity(), View.OnClickListener {

    //creating an object for project category class
    var project = ProjectCategory()

    // Declaring clickCount as a class-level property
    var clickCount = 0

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //setting the layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //using dataBinding to inflate the activity dashboard on the screen
        binding = ActivityDashboardBinding.inflate(layoutInflater)

        // Retrieve clickCount from intent extras
        clickCount = intent.getIntExtra("clickCount", 0)

        // Adding the functionality when the user clicks something
        binding.btnwork.setOnClickListener(this)
        binding.btnschool.setOnClickListener(this)
        binding.btngeneral.setOnClickListener(this)
        binding.btnworklogo.setOnClickListener(this)
        binding.btnschoolLogo.setOnClickListener(this)
        binding.btngeneralLogo.setOnClickListener(this)

        //setting the content view
        setContentView(binding.root)

        // Set click listener for gameBtn button using view binding
        binding.gameBtn.setOnClickListener {
            // Redirect to the game page
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        //method to bind the values with the buttons
        binding.btnSubmit.setOnClickListener {
            val projectName = binding.txtProjectName.text.toString()
            val projectTimeSpent = binding.txtProjectTimeSpent.text.toString()
            val projectTimeLeft = binding.txtProjectTimeLeft.text.toString()

            //validating the text fields if they were empty of with all data entered
            if (projectName.isNotEmpty() && projectTimeSpent.isNotEmpty() && projectTimeLeft.isNotEmpty()) {
                Toast.makeText(this, "Project details successfully entered", Toast.LENGTH_SHORT)
                    .show()
                //calling the next screen to be shown
                val intent = Intent(this, AddProject::class.java)
                //starting the activity
                startActivity(intent)
            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View?) {
        // Handle click events for the buttons
        when (v?.id) {
            R.id.btnwork -> {
                project.projectCategory = "Work"
                clickCount++
                binding.btnwork.text = "Work ($clickCount)"
                // Redirect user to add existing project page
                val intent = Intent(this, ExistingProject::class.java)
                startActivity(intent)
            }
            R.id.btnschool -> {
                project.projectCategory = "School"
                clickCount++
                binding.btnwork.text = "School ($clickCount)"
                // Redirect user to add existing project page
                val intent = Intent(this, ExistingProject::class.java)
                startActivity(intent)
            }
            R.id.btngeneral -> {
                project.projectCategory = "General"
                clickCount++
                binding.btnwork.text = "General ($clickCount)"
                // Redirect user to add existing project page
                val intent = Intent(this, ExistingProject::class.java)
                startActivity(intent)
            }
            R.id.btnschoolLogo -> {
                project.projectCategory = "School"
                Toast.makeText(this@Dashboard,"You have added a new School project", Toast.LENGTH_SHORT)
                    .show()
                // Redirect user to add new project page
                val intent = Intent(this, AddProject::class.java)
                startActivity(intent)
            }
            R.id.btnworklogo -> {
                project.projectCategory = "Work"
                Toast.makeText(this@Dashboard,"You have added a new Work project", Toast.LENGTH_SHORT)
                    .show()
                // Redirect user to add new project page
                val intent = Intent(this, AddProject::class.java)
                startActivity(intent)
            }
            R.id.btngeneralLogo -> {
                project.projectCategory = "General"
                Toast.makeText(this@Dashboard,"You have added a new General project", Toast.LENGTH_SHORT)
                    .show()
                // Redirect user to add new project page
                val intent = Intent(this, AddProject::class.java)
                startActivity(intent)
            }
        }
    }
}
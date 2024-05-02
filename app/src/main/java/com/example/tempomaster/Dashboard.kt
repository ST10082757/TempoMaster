package com.example.tempomaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tempomaster.com.example.tempomaster.ProjectCategory
import com.example.tempomaster.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity(), View.OnClickListener {

    //creating an object for project category class
    var project = ProjectCategory()

    // Click counts for each category
    var workClickCount = 0
    var schoolClickCount = 0
    var generalClickCount = 0

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //setting the layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
// Retrieve click counts from intent extras
        workClickCount = intent.getIntExtra("workClickCount", 0)
        schoolClickCount = intent.getIntExtra("schoolClickCount", 0)
        generalClickCount = intent.getIntExtra("generalClickCount", 0)
        //using dataBinding to inflate the activity dashboard on the screen

        val binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Set the text of the buttons based on click counts
        binding.btnwork.text = "Work ($workClickCount)"
        binding.btnschool.text = "School ($schoolClickCount)"
        binding.btngeneral.text = "General ($generalClickCount)"


        // Adding the functionality when the user clicks something
        binding.btnwork.setOnClickListener(this)
        binding.btnschool.setOnClickListener(this)
        binding.btngeneral.setOnClickListener(this)
        binding.btnworklogo.setOnClickListener(this)
        binding.btnschoolLogo.setOnClickListener(this)
        binding.btngeneralLogo.setOnClickListener(this)

        //setting the content view
       // setContentView(binding.root)

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

        // Check initialization of the bottom navigation
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboardID -> { /* Already in Dashboard */ }
                R.id.settingsID -> {
                    val intent = Intent(this, Settings::class.java)
                    startActivity(intent)
                }
                R.id.projectID -> {
                    val intent = Intent(this, ProjectList::class.java)
                    startActivity(intent)
                }
                else -> false
            }
            true // Indicate successful handling
        }

    }


    override fun onClick(v: View?) {
        // Handle click events for the buttons
        when (v?.id) {
            R.id.btnwork -> {
                // Increment click count for Work button
                workClickCount++
                binding.btnwork.text = "Work ($workClickCount)"
                // Redirect user to add existing project page
                val intent = Intent(this, ExistingProject::class.java)
                startActivity(intent)
            }
            R.id.btnschool -> {
                // Increment click count for School button
                schoolClickCount++
                binding.btnschool.text = "School ($schoolClickCount)"
                // Redirect user to add existing project page
                val intent = Intent(this, ExistingProject::class.java)
                startActivity(intent)
            }
            R.id.btngeneral -> {
                // Increment click count for General button
                generalClickCount++
                binding.btngeneral.text = "General ($generalClickCount)"
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

                // Start Dashboard activity and pass click counts
                val intent = Intent(this, Dashboard::class.java)
                intent.putExtra("workClickCount", workClickCount)
                intent.putExtra("schoolClickCount", schoolClickCount)
                intent.putExtra("generalClickCount", generalClickCount)
                startActivity(intent)

            }
        }
    }
}
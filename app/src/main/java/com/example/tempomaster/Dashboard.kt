package com.example.tempomaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tempomaster.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity(), View.OnClickListener {

    var project = ProjectCategory()

    override fun onCreate(savedInstanceState: Bundle?) {
        //setting the layout
        setContentView(R.layout.activity_dashboard)
        super.onCreate(savedInstanceState)

        //using dataBinding to inflate the activity dashboard on the screen
        val binding = ActivityDashboardBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        //adding the functionality when user clicks something
        binding.btnwork.setOnClickListener(this)
        binding.btnschool.setOnClickListener(this)
        binding.btngeneral.setOnClickListener(this)
        binding.btnworklogo.setOnClickListener(this)
        binding.btnschoolLogo.setOnClickListener(this)
        binding.btngeneralLogo.setOnClickListener(this)


        //setting the content view
        setContentView(binding.root)

        //method to bind the values with the buttons
        binding.btnSubmit.setOnClickListener{
            val projectName = binding.txtProjectName.text.toString()
            val projectTimeSpent = binding.txtProjectTimeSpent.text.toString()
            val projectTimeLeft = binding.txtProjectTimeLeft.text.toString()

            //validating the text fields if they were empty of with all data entered
            if(projectName.isNotEmpty() && projectTimeSpent.isNotEmpty() && projectTimeLeft.isNotEmpty())
            {
                Toast.makeText(this,"Project details successfully entered",Toast.LENGTH_SHORT).show()
                //calling the next screen to be shown
                val intent = Intent(this,AddProject::class.java)
                //starting the activity
                startActivity(intent)
            } else
            {
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View?)
        {
            //determining which view has been clicked based on ID
            when(v?.id)
            {
                R.id.btnwork -> project.projectCategory = "Work"
                R.id.btnschool -> project.projectCategory = "School"
                R.id.btngeneral -> project.projectCategory = "General"
                R.id.btnworklogo -> project.projectCategory = "Work"
                R.id.btnschoolLogo -> project.projectCategory = "School"
                R.id.btngeneralLogo -> project.projectCategory = "General"
            }
    }
}
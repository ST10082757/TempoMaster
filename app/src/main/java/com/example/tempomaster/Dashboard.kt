package com.example.tempomaster

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tempomaster.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {

    var project = ProjectCategory()

    override fun onCreate(savedInstanceState: Bundle?) {
        //setting the layout
        setContentView(R.layout.activity_dashboard)
        super.onCreate(savedInstanceState)

        //using dataBinding to inflate the activity dashboard on the screen
        val binding = ActivityDashboardBinding.inflate(layoutInflater)

        //setting the content view
        setContentView(binding.root)

        //getting the project category from the intent
        project.projectCategory = intent.getStringExtra("project").toString()


        //setting the project categories
        when(project.projectCategory)
        {
            "Work" -> binding.btnworklogo.setBackgroundResource(R.drawable.work_on_computer_employee_software_engineer_programmer_svgrepo_com)
            "School" -> binding.btnschoolLogo.setBackgroundResource(R.drawable.school_svgrepo_com)
            "General" -> binding.btngeneralLogo.setBackgroundResource(R.drawable.productivity_ability_talent_productive_svgrepo_com)
        }

        binding.floatingActionButton3.setOnClickListener()
        {
            shareIntent(this,order.productName)
        }





    }
}
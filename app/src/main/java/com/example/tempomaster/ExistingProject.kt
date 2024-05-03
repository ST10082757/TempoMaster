package com.example.tempomaster

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Bitmap
import android.widget.ImageView

class ExistingProject : AppCompatActivity() {
    private var iintent = TheIntentHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_existing_project)

        // insets
// the list of projects
        val bundle = intent.extras
        val date = bundle?.getString("Date")
        val projectName = bundle?.getString("Project Name")
        val description = bundle?.getString("Description")
        val startTime = bundle?.getString("Start Time")
        val endTime = bundle?.getString("End Time")

//displaying the projects in a ListView
        val listOfProjects : ListView = findViewById(R.id.ListOfProjects)
        val projectList = arrayListOf("$projectName, $description, $date, $startTime, $endTime")
        /*
        this= referring to the current Activity
        android.R.layout.simple_list_item_1= This is a built-in layout provided by
        Android Studio that represents a single item in the list.
        projectList= This is the data(project details) that the adapter will use to fill the ListView
         */
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,projectList )
        listOfProjects.adapter = adapter


        val deleteBtn = findViewById<Button>(R.id.DeleteBtn)
        deleteBtn.setOnClickListener{
            //based on what is chosen
            val selectedItemPosition = listOfProjects.checkedItemPosition
            if(selectedItemPosition != ListView.INVALID_POSITION){
                //delete the project chosen
                projectList.removeAt(selectedItemPosition)

                //toast message alerting the user
                adapter.notifyDataSetChanged()} else{
                Toast.makeText(this,"No items selected", Toast.LENGTH_SHORT).show()
            }

        }

        val  button = findViewById<Button>(R.id.rtnBackBtn)
        button.setOnClickListener{
            iintent.startAddProjectActivity(this,AddProject::class.java)
        }

        //for the camera intent
        val image : ImageView = findViewById(R.id.projectPngView)
        val bitmap = intent.getParcelableExtra<Bitmap>("ProjectImage")
        image.setImageBitmap(bitmap)
    }
}
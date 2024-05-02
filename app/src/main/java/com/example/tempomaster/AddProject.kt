package com.example.tempomaster

import android.view.View
import android.view.View.OnClickListener
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts


class AddProject : AppCompatActivity() {

    private val requestImageCapture = 1

    private lateinit var resultLauncher:ActivityResultLauncher<Intent> //for photo
    //Create a new Projects object
    val projects = Projects()

    private lateinit var calendarView: CalendarView

    var intentHelper = TheIntentHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_project)

        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        // val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //insets
        // Get references to your input fields
        //val dateInput = findViewById<EditText>(R.id.txtDate)

        calendarView = findViewById(R.id.projectCalendar)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val DateSelected = "$dayOfMonth-${month + 1}-$year"
            projects.date = DateSelected // Set the date in  Projects object

            val projectNameInput = findViewById<EditText>(R.id.AddProjName)
            val descriptionInput = findViewById<EditText>(R.id.Descriptiontxt)
            val startTimeInput = findViewById<EditText>(R.id.txtstartTime)
            val endTimeInput = findViewById<EditText>(R.id.txtEndTime)
            val category = findViewById<EditText>(R.id.txtCategory)
//----------------------------------------------------------------------------------------------
            /*
        /after the button click(below), we are getting and setting
        the input values to their fields accoridng to the
        Projects object. The bundle will help display the into
        into the Existing projects activity
         */
            // Get reference to your button
            val clickToAddProj = findViewById<Button>(R.id.clickAddPrj)

            // Set up a click listener for your button
            clickToAddProj.setOnClickListener {
                // Get the input field values
                val date = DateSelected
//according to copilot, this is a way to use the string directly without the '.text'
                val projectName = projectNameInput.text.toString()
                val description = descriptionInput.text.toString()
                val startTime = startTimeInput.text.toString()
                val endTime = endTimeInput.text.toString()

// Create a Bundle to hold the data
                val bundle = Bundle()
                bundle.putString("Date", date)
                bundle.putString("Project Name", projectName)
                bundle.putString("Description", description)
                bundle.putString("Start Time", startTime)
                bundle.putString("End Time", endTime)
                //using the intent helper to send data to Existing project activity
                //
                intentHelper.startExistingProjectActivity(this, ExistingProject::class.java, bundle)
                // intentHelper.startingExistingProjectActivity(this, ExistingProject::class.java)
            }
        }
       /* val fab = findViewById<FloatingActionButton>(R.id.fab_ViewProjects)
        fab.setOnClickListener {
            val intent = Intent(this, ExistingProject::class.java)
            startActivity(intent)

        }*/
//taking a photo using camera intent

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                val intent = Intent(this, ExistingProject::class.java).apply {
                    putExtra("ProjectImage", imageBitmap)
                }
                startActivity(intent)
            }
        }

        //camera button click for photo capture
        val camButton = findViewById<Button>(R.id.cameraBtn)
        camButton.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    resultLauncher.launch(takePictureIntent)
                }
            }
        }
    }
}



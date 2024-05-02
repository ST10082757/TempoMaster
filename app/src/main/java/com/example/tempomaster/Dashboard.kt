package com.example.tempomaster

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import android.content.Intent

class Dashboard : AppCompatActivity() {
    var intent = TheIntentHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Get a reference to your button
        val button = findViewById<Button>(R.id.btnschoolLogo)
        button.setOnClickListener {
   //using the Intent Helper class
            intent.startAddProjectActivity(this,AddProject::class.java)
        }
        /* Assuming 'button' is your Button view
        button.setOnClickListener {
            val intent = Intent(this, AddProject::class.java)

            // Check if conditions to open the activity are met
            if (/* condition */) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error message", Toast.LENGTH_SHORT).show()
            }
        }*/

    }
    }

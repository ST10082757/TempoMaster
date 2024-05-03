package com.example.tempomaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Goals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_goals)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val minGoalEditText = findViewById<EditText>(R.id.minGoal)
        val maxGoalEditText = findViewById<EditText>(R.id.maxGoal)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val backButton = findViewById<Button>(R.id.backButton)

        saveButton.setOnClickListener {
            val minGoal = minGoalEditText.text.toString()
            val maxGoal = maxGoalEditText.text.toString()

            // Pass the goals to the next activity (Dashboard)
            val intent = Intent(this, Dashboard::class.java)
            intent.putExtra("minGoal", minGoal)
            intent.putExtra("maxGoal", maxGoal)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            // Navigate back to the dashboard
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}
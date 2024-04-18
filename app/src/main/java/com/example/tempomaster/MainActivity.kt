package com.example.tempomaster

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

      val signupbtn = findViewById<Button>(R.id.btn_signup)
        signupbtn.setOnClickListener{
            val Intent = Intent(this, SignUp::class.java)
            startActivity(Intent)
        }
    }
}

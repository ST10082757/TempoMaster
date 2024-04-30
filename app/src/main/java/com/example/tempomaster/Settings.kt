package com.example.tempomaster

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tempomaster.databinding.ActivitySettingsBinding
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.graphics.Color
import android.widget.Button
import androidx.core.app.NotificationCompat


 class Settings : AppCompatActivity() {
    // Declaration of the binding variable at the class level
    private lateinit var binding: ActivitySettingsBinding
    // notification channel ID
    private val focusNotificationChannelId ="focus_channel"


    // Define the fragments to be used in your activity
    private lateinit var dashboard: Fragment
    private lateinit var existingProjects: Fragment
    private lateinit var settings: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setting the click listener for the game button
        binding.gameBtn.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)  // Navigate to GameActivity
            startActivity(intent)
        }
        // Set click listener to navigate to the About page
        binding.aboutBtn.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        // Subscription button
        binding.subscriptionBtn.setOnClickListener(){
            val intent = Intent(this,SubscriptionActivity::class.java)
            startActivity(intent)
        }
        // Enable edge-to-edge design
        enableEdgeToEdge()

        //Creating the notification channel
        createNotificationChannel(this)

        // setting the click listener for focus mode
        binding.focusBtn.setOnClickListener(){
            showFocusNotification(this)
        }
        // Window insets listener for setting padding based on system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        // BottomNavigationView item selection listener
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboardID -> replaceFragment(dashboard)
                R.id.projectID -> replaceFragment(existingProjects)
                R.id.settingsID -> replaceFragment(settings)
            }
            true
        }
    }

   private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Focus Notification"
            val descriptionText = "Focus mode notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(focusNotificationChannelId, name, importance).apply {
                    description = descriptionText
                    lightColor = Color.BLUE
                }
            // Register the notification channel to the system
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
            //-----------------------------------------------------//
            //-------------------Notifications to display----------------------//
            private fun showFocusNotification(context: Context) {
                val notification = NotificationCompat.Builder(context, focusNotificationChannelId)
                        .setSmallIcon(R.drawable.focus6)
                        .setContentTitle("Focus Mode")
                        .setContentText("Focus mode is on")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build()
                //To display the notification
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(1, notification)
            }

    // Fragment replacement method
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null) // Optional, for back navigation
        fragmentTransaction.commit()
    }
}




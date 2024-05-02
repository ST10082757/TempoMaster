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
import android.content.SharedPreferences
import android.os.Build
import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat


 class Settings : AppCompatActivity() {

     // Declaration of the binding variable at the class level
     private lateinit var binding: ActivitySettingsBinding
     private lateinit var sharedPreferences: SharedPreferences

     // notification channel ID
     private val focusNotificationChannelId = "focus_channel"

     // Define the fragments to be used in your activity
     private lateinit var dashboard: Fragment
     private lateinit var projectList: Fragment
     private lateinit var settings: Fragment


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_settings)

         // Initialize the binding
         binding = ActivitySettingsBinding.inflate(layoutInflater)
         setContentView(binding.root)

         //new nav code
         binding.bottomNavigationView.setOnItemSelectedListener { item ->
             when (item.itemId) {
                 R.id.dashboardID -> replaceFragment(dashboard)
                 R.id.projectID -> replaceFragment(projectList)
                 else -> false
             }
             true // Indicate successful handling
         }

         //_________________________Navigation bar_____________________________//
         binding.bottomNavigationView.setOnItemSelectedListener { item ->
             when (item.itemId) {
                 R.id.dashboardID -> {
                     val intent = Intent(this, Dashboard::class.java)
                     startActivity(intent)
                     true // Successful handling
                 }
                 R.id.projectID -> {
                     val intent = Intent(this, ProjectList::class.java)
                     startActivity(intent)
                     true // Successful handling
                 }
                 else -> false // Unhandled case
             }
         }

         val settingspageTitle = binding.settingspageTitle
         //Dark mode switch
         sharedPreferences = getSharedPreferences("ThemePreferences", MODE_PRIVATE)

         // Initialize the Switch and set its initial state based on preferences
         val themeSwitch = binding.switch1 // Update with the correct ID
         val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
         themeSwitch.isChecked = isDarkMode

         // Set the app theme according to the Switch state
         setAppTheme(isDarkMode, binding)

         // Handle the Switch click event
         binding.switch1.setOnCheckedChangeListener { _, isChecked ->
             setAppTheme(isChecked, binding)
             sharedPreferences.edit().putBoolean("isDarkMode", isChecked).apply()
         }

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
         binding.subscriptionBtn.setOnClickListener() {
             val intent = Intent(this, SubscriptionActivity::class.java)
             startActivity(intent)
         }
         // Enable edge-to-edge design
         enableEdgeToEdge()

         //Creating the notification channel
         createNotificationChannel(this)

         // setting the click listener for focus mode
         binding.focusBtn.setOnClickListener() {
             showFocusNotification(this)
         }
         // Window insets listener for setting padding based on system bars
         ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
             WindowInsetsCompat.CONSUMED
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
             val notificationManager: NotificationManager =
                 context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
             notificationManager.createNotificationChannel(channel)
         }
     }
     //------------------------------------------------------------------------------------------//
     //------------------------Notifications to display----------------------//
     private fun showFocusNotification(context: Context) {
         val notification = NotificationCompat.Builder(context, focusNotificationChannelId)
             .setSmallIcon(R.drawable.focus6)
             .setContentTitle("Focus Mode")
             .setContentText("Focus mode is on")
             .setPriority(NotificationCompat.PRIORITY_DEFAULT)
             .build()
         //To display the notification
         val notificationManager =
             context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
         notificationManager.notify(1, notification)
     }

     // Fragment replacement method
     private fun replaceFragment(fragment: Fragment) {
         val fragmentManager = supportFragmentManager
         val fragmentTransaction = fragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.frame_layout, fragment) // Target fragment container
         fragmentTransaction.commit() // Commit the fragment transaction
     }

     // Function to set the theme based on the switch state
     private fun setAppTheme(isDarkMode: Boolean, binding: ActivitySettingsBinding) {
         // Set the app's night mode setting based on isDarkMode
         val nightMode = if (isDarkMode) {
             AppCompatDelegate.MODE_NIGHT_YES
         } else {
             AppCompatDelegate.MODE_NIGHT_NO
         }
         AppCompatDelegate.setDefaultNightMode(nightMode)

         // Update the UI elements based on dark mode status
         if (isDarkMode) {
             binding.root.setBackgroundColor(Color.BLACK) // Set background to black
             // Update text colors to white
             binding.settingspageTitle.setTextColor(Color.WHITE) // Example TextView

         } else {
             binding.root.setBackgroundColor(Color.WHITE) // Set background to white
             // Update text colors to black
             binding.settingspageTitle.setTextColor(Color.BLACK)
         }
     }
 }
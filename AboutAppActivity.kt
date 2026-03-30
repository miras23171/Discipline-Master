package com.example.disciplinemaster

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AboutAppActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var contactButton: Button
    private lateinit var shareButton: Button
    private lateinit var rateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)

        backButton = findViewById(R.id.backButton)
        contactButton = findViewById(R.id.contactButton)
        shareButton = findViewById(R.id.shareButton)
        rateButton = findViewById(R.id.rateButton)

        backButton.setOnClickListener { finish() }
        contactButton.setOnClickListener { sendFeedback() }
        shareButton.setOnClickListener { shareApp() }
        rateButton.setOnClickListener { rateApp() }
    }

    private fun sendFeedback() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("miras@example.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Feedback - Discipline Master")
            putExtra(Intent.EXTRA_TEXT, "Share your feedback here...")
        }
        try {
            startActivity(Intent.createChooser(intent, "Send Feedback"))
        } catch (e: Exception) {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareApp() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out Discipline Master - Build daily discipline through prayer, reading, and exercise! https://play.google.com/store/apps/details?id=com.example.disciplinemaster")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share App"))
    }

    private fun rateApp() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.example.disciplinemaster")))
        } catch (e: Exception) {
            Toast.makeText(this, "Could not open Play Store", Toast.LENGTH_SHORT).show()
        }
    }
}
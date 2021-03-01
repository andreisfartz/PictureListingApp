package com.asfartz.picturelistingapp.hermetic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.asfartz.picturelistingapp.MyApplication
import com.asfartz.picturelistingapp.R
import org.joda.time.DateTime

class GreetingMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting_main)

        val app = application as MyApplication
        val now = app.provideClock().getNow()

        val greetingId = when (now.hourOfDay) {
            in 5..12 -> R.string.greeting_morning
            in 12..17 -> R.string.greeting_afternoon
            in 17..23 -> R.string.greeting_evening
            else -> R.string.greeting_night
        }

        val greetingView = findViewById<TextView>(R.id.greeting)
        greetingView.setText(greetingId)
    }
}
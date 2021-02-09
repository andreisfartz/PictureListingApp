package com.asfartz.picturelistingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private val TAG = "MainActivity"
    private lateinit var searchImagesBtn: Button
    private lateinit var randomImagesBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchImagesBtn = findViewById(R.id.btn_search_images)
        randomImagesBtn = findViewById(R.id.btn_random_images)
        searchImagesBtn.setOnClickListener(this)
        randomImagesBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_search_images -> {
                val intent = Intent(this, SearchPicturesActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_random_images -> {
                val intent = Intent(this, SearchPicturesActivity::class.java)
                startActivity(intent)
            }
        }
    }


}
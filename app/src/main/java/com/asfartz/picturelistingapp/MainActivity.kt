package com.asfartz.picturelistingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private val TAG = "MainActivity"
    private lateinit var searchImagesBtn: Button
    private lateinit var randomImagesBtn: Button
    private lateinit var showNameBtn: Button
    private lateinit var firstNameTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.app_name_sep)

        searchImagesBtn = findViewById(R.id.btn_search_images)
        randomImagesBtn = findViewById(R.id.btn_random_images)
        showNameBtn = findViewById(R.id.btn_show_name)
        firstNameTV = findViewById(R.id.tv_first_name)

        searchImagesBtn.setOnClickListener(this)
        randomImagesBtn.setOnClickListener(this)
        showNameBtn.setOnClickListener(this)
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

            R.id.btn_show_name -> {
                showFirstName()
            }
        }
    }

    private fun showFirstName() {
        showNameBtn.isEnabled = false
        firstNameTV.text = MyApplication.getFirstName()
    }


}
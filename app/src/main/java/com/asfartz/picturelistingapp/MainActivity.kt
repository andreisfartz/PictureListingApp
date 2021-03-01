package com.asfartz.picturelistingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.asfartz.picturelistingapp.catnames.CatNamesMainActivity
import com.asfartz.picturelistingapp.pictures.SearchPicturesActivity

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private val TAG = "MainActivity"
    private lateinit var searchImagesBtn: Button
    private lateinit var randomImagesBtn: Button
    private lateinit var listViewActBtn: Button
    private lateinit var recyclerViewActBtn: Button
    private lateinit var showNameBtn: Button
    private lateinit var catsBtn: Button
    private lateinit var firstNameTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.app_name_sep)

        searchImagesBtn = findViewById(R.id.btn_search_images)
        randomImagesBtn = findViewById(R.id.btn_random_images)
        listViewActBtn = findViewById(R.id.btn_list_view)
        recyclerViewActBtn = findViewById(R.id.btn_recycler_view)
        showNameBtn = findViewById(R.id.btn_show_name)
        catsBtn = findViewById(R.id.btn_cats)
        firstNameTV = findViewById(R.id.tv_first_name)

        searchImagesBtn.setOnClickListener(this)
        randomImagesBtn.setOnClickListener(this)
        listViewActBtn.setOnClickListener(this)
        recyclerViewActBtn.setOnClickListener(this)
        showNameBtn.setOnClickListener(this)
        catsBtn.setOnClickListener(this)
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

            R.id.btn_list_view -> {
                val intent = Intent(this, ListViewActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_recycler_view -> {
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_cats -> {
                val intent = Intent(this, CatNamesMainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showFirstName() {
        showNameBtn.isEnabled = false
        firstNameTV.text = MyApplication.getFirstName()
    }


}
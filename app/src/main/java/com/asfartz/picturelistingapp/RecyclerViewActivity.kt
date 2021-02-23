package com.asfartz.picturelistingapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val footer = findViewById<TextView>(R.id.footer)
        footer.setBackgroundColor(Color.LTGRAY)
        footer.visibility = View.GONE

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NumberedAdapter(30, object : NumberedAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                footer.text = position.toString()
                footer.visibility = View.VISIBLE
            }
        })
    }

}

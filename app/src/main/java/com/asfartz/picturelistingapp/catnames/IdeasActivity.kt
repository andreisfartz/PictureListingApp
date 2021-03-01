package com.asfartz.picturelistingapp.catnames

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asfartz.picturelistingapp.R

class IdeasActivity : AppCompatActivity() {
    companion object {
        const val KEY_THEME = "theme"
        const val KEY_NAME = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ideas)

        val ideasTitleView = findViewById<TextView>(R.id.theme_title)

        val ideasTitleTheme = intent.getStringExtra(KEY_THEME)
        if (ideasTitleTheme == null) {
            ideasTitleView.setText(R.string.missing_theme)
            return
        }

        ideasTitleView.text = ideasTitleTheme

        val ideasId = when (ideasTitleTheme) {
            getString(R.string.theme_popular) -> R.array.ideas_popular
            getString(R.string.theme_famous) -> R.array.ideas_famous
            getString(R.string.theme_punny) -> R.array.ideas_punny
            else -> 0
        }

        if (ideasId == 0) {
            ideasTitleView.text = getString(R.string.unknown_theme, ideasTitleTheme)
            return
        }

        val ideas = resources.getStringArray(ideasId)

        val recyclerView = findViewById<RecyclerView>(R.id.ideas)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = IdeasAdapter(ideas, object : IdeasAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val data = Intent()
                data.putExtra(KEY_NAME, ideas[position])
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        })
    }
}
package com.asfartz.picturelistingapp

import android.app.Application
import android.content.res.Configuration
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.asfartz.picturelistingapp.hermetic.Clock
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker

open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        UnsplashPhotoPicker.init(
            this, // application
            "j4P7SWSmIv-DIdBJFHeI0bodIVwFFWxxCPnkKqvb97w",
            "2iVjt7FfSqrtK0FAaFCShSmOhUO_dijlhFpMvgRgmG4"
            /* optional page size */
        )

    }

    open fun provideClock(): Clock = Clock()

    companion object {
        fun getUnsplashAPIKey() = "j4P7SWSmIv-DIdBJFHeI0bodIVwFFWxxCPnkKqvb97w"

        fun getFirstName() = "Andrei"
    }

}
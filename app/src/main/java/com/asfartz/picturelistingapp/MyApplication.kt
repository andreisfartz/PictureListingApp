package com.asfartz.picturelistingapp

import android.app.Application
import android.content.res.Configuration
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        UnsplashPhotoPicker.init(
            this, // application
            "j4P7SWSmIv-DIdBJFHeI0bodIVwFFWxxCPnkKqvb97w",
            "2iVjt7FfSqrtK0FAaFCShSmOhUO_dijlhFpMvgRgmG4"
            /* optional page size */
        )

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    companion object {
        fun getUnsplashAPIKey() = "j4P7SWSmIv-DIdBJFHeI0bodIVwFFWxxCPnkKqvb97w"

        fun getFirstName() = "Andrei"
    }

}
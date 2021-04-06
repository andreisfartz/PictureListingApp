package com.asfartz.picturelistingapp.hermetic

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        // return super.newApplication(cl, className", context)
        return super.newApplication(cl, "com.asfartz.picturelistingapp.hermetic.TestApplication", context)
    }
}
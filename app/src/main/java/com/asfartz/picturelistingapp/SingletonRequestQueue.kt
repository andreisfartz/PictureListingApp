package com.asfartz.picturelistingapp

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class SingletonRequestQueue {

    //how to add context at class initialization, and use it in the companion object ?
    companion object {
        @Volatile
        private var INSTANCE: RequestQueue? = null
        fun getInstance(context: Context): RequestQueue = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Volley.newRequestQueue(context).also { INSTANCE = it }
        }
    }

}
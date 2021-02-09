package com.asfartz.picturelistingapp

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class SingletonRequestQueueTest {

    lateinit var instrumentationContext: Context

    @Before
    fun setUp() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun RequestQueue_is_same_Instance() {
        val rq1 = SingletonRequestQueue.getInstance(instrumentationContext)
        val rq2 = SingletonRequestQueue.getInstance(instrumentationContext)
        assertEquals(rq1, rq2)
    }

    @After
    fun tearDown() {
    }
}
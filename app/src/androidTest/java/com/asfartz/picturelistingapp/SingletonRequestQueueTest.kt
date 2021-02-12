package com.asfartz.picturelistingapp

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SingletonRequestQueueTest {

    lateinit var appContext: Context

    @Before
    fun setUp() {

        appContext = InstrumentationRegistry.getInstrumentation().targetContext

    }

    @Test
    fun test_appContext() {
        assertEquals("com.asfartz.picturelistingapp", appContext.packageName)
    }

    @Test
    fun test_RequestQueue_is_Singleton() {
        val rq1 = SingletonRequestQueue.getInstance(appContext)
        val rq2 = SingletonRequestQueue.getInstance(appContext)
        assertEquals(rq1, rq2)
    }

    @After
    fun tearDown() {
//        appContext = null   // Null can not be a value of a non-null type Context
    }
}
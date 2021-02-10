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
        // InstrumentationRegistry - an exposed registry instance that holds a ref to the instrumentation running in the process and it's arguments
        // getInstrumentation() - the Instrumentation currently running
        // getContext() - the Context of this Instrumentation's package
        // getTargetContext() - the application Context of the target application
        // getArguments - copy of arguments Bundle. Useful for accessing the command line args passed to the Instruentation for the test

        // Note: When running Instrumentation tests, there are 2 apps: the 'real' app, and the 'test' app.
        // load a res from real app: getTargetContext()
        // load a res from test app: getContext()
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
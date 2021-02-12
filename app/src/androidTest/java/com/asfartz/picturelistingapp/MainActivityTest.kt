package com.asfartz.picturelistingapp

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.view.View
import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.instanceOf
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    companion object {

        // variables initialized for the test class, JUST ONCE
        lateinit var appContext: Context

        @JvmStatic
        @BeforeClass
        fun setUp() {
            // executed once, before running the class. ex: database connections
            appContext = InstrumentationRegistry.getInstrumentation().targetContext
        }

        @JvmStatic
        @AfterClass
        fun tearDown() {
            // executed once, after all tests have run. ex: clear connections, ports, etc
            appContext = InstrumentationRegistry.getInstrumentation().targetContext
        }
    }

    // variables initialized PER instance of the test class
    //  var a = ..

    @get:Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(MainActivity::class.java, false, true)

    @get:Rule
    val mainActivityScenario: ActivityScenario<MainActivity> =
        ActivityScenario.launch(MainActivity::class.java)

    @get:Rule
    val searchPicturesActivityScenario: ActivityScenario<SearchPicturesActivity> =
        ActivityScenario.launch(SearchPicturesActivity::class.java)


    @Test
    fun test_MainActivity_views_are_correctly_initialized() {
        val mainActivity: MainActivity = mainActivityTestRule.activity
        val searchImagesBtn: View = mainActivity.findViewById(R.id.btn_search_images)
        val randomImagesBtn: View = mainActivity.findViewById(R.id.btn_random_images)
        assertNotNull(searchImagesBtn)
        assertNotNull(randomImagesBtn)
        assertThat(searchImagesBtn, instanceOf(Button::class.java))
        assertThat(randomImagesBtn, instanceOf(Button::class.java))

        onView(withId(R.id.btn_search_images))
            .check(matches(withText(appContext.resources.getString(R.string.search_images))))
        onView(withId(R.id.btn_random_images))
            .check(matches(withText(appContext.resources.getString(R.string.find_a_random_image))))

    }


    @Test
    fun test_onClick_Button_starts_Activity() {
        val searchPicturesActivityMonitor: Instrumentation.ActivityMonitor =
            InstrumentationRegistry.getInstrumentation()
                .addMonitor(SearchPicturesActivity::class.simpleName, null, false)

        onView(withId(R.id.btn_search_images)).perform(click())

        val searchPicturesActivity: Activity? =
            searchPicturesActivityMonitor.waitForActivityWithTimeout(8 * 1000)
        assertNotNull("searchPicturesActivity not started after 10s", searchPicturesActivity)

        assertEquals(Lifecycle.State.RESUMED, searchPicturesActivityScenario.state)

    }
}
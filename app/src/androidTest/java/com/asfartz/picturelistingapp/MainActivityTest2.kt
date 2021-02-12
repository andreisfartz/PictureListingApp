package com.asfartz.picturelistingapp

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainActivityTest2 {

    lateinit var appContext: Context

    private val scenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    private val scenario: ActivityScenario<MainActivity> =
        ActivityScenario.launch(MainActivity::class.java)

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @After
    fun tearDown() {

    }

    @Test
    fun test_MainActivity_views_are_correctly_initialized() {
        ActivityScenario.launch(MainActivity::class.java).onActivity {
            val searchImagesBtn: Button = it.findViewById(R.id.btn_search_images)
            val randomImagesBtn: Button = it.findViewById(R.id.btn_random_images)

            Assert.assertNotNull(searchImagesBtn)
            Assert.assertNotNull(randomImagesBtn)
            Assert.assertThat(searchImagesBtn, Matchers.instanceOf(Button::class.java))
            Assert.assertThat(randomImagesBtn, Matchers.instanceOf(Button::class.java))

            // check text  #option 1
            Espresso.onView(ViewMatchers.withId(R.id.btn_search_images))
                .check(ViewAssertions.matches(ViewMatchers.withText(appContext.resources.getString(R.string.search_images))))
            Espresso.onView(ViewMatchers.withId(R.id.btn_random_images))
                .check(ViewAssertions.matches(ViewMatchers.withText(appContext.resources.getString(R.string.find_a_random_image))))

            // check text #option 2
            assertEquals(searchImagesBtn.text, it.resources.getString(R.string.search_images))
            assertEquals(randomImagesBtn.text, it.resources.getString(R.string.find_a_random_image))

        }


    }


    @Test
    fun test_onClick_Button_starts_Activity() {
        // try(ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
        // /*...*/
        // }
        //
        // how do you write try-with-resource in Kotlin ???
        //
        // ActivityScenario.launch(MainActivity::class.java).use {
        //     assertThat(it.state, equalTo(Lifecycle.State.RESUMED))
        // }


        Espresso.onView(ViewMatchers.withId(R.id.btn_search_images)).perform(ViewActions.click())
//        val activityMonitor: Instrumentation.ActivityMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(
//            SearchPicturesActivity::class.simpleName
//        )
    }
}
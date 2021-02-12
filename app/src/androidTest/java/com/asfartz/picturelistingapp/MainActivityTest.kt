package com.asfartz.picturelistingapp

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.instanceOf
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    lateinit var appContext: Context

    @get:Rule
    val intentsTestRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @get:Rule
     val activityTestRule: ActivityTestRule<MainActivity> =
         ActivityTestRule<MainActivity>(MainActivity::class.java, false, true)


    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @After
    fun tearDown() {

    }

    @Test
    fun test_MainActivity_views_are_correctly_initialized() {
        val mainActivity: MainActivity = activityTestRule.activity
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
        onView(withId(R.id.btn_search_images)).perform(click())
//        val activityMonitor: Instrumentation.ActivityMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(
//            SearchPicturesActivity::class.simpleName
//        )
    }
}
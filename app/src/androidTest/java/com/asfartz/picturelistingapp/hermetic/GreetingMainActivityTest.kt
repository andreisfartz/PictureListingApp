package com.asfartz.picturelistingapp.hermetic

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.asfartz.picturelistingapp.R
import org.joda.time.DateTime
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class GreetingMainActivityTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule(GreetingMainActivity::class.java, true, false)

    @Test
    fun evening() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication
        val clock = app.provideClock()
        Mockito.`when`(clock.getNow()).thenReturn(DateTime().withHourOfDay(20))

        activityRule.launchActivity(null)
        onView(withId(R.id.greeting))
            .check(matches(withText(R.string.greeting_evening)))
    }

}
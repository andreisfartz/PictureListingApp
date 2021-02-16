package com.asfartz.picturelistingapp

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.AfterClass
import org.junit.Assert.assertNotNull
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    companion object {
        // variables initialized for the test class, JUST ONCE
        lateinit var appContext: Context

        // executed once, before / after running all tests. Ex: clear connections, ports, etc
        @BeforeClass
        @JvmStatic
        fun setUp() {
            appContext = InstrumentationRegistry.getInstrumentation().targetContext
        }

        @AfterClass
        @JvmStatic
        fun tearDown() {
            appContext = InstrumentationRegistry.getInstrumentation().targetContext
        }
    }

    // @get:Rule
    @Rule
    @JvmField
    var mainActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun showFirstName() {
        onView(withId(R.id.tv_first_name))
            .check(matches(withText("")))

        onView(withId(R.id.btn_show_name))
            .perform(click())
            .check(matches(not(isEnabled())))

        onView(withId(R.id.tv_first_name))
            .check(matches(withText(R.string.name_Andrei)))
    }

    @Test
    fun toolbarTitle() {
        // isAssignableFrom(TextView::class.java) = we are looking for a view that is a TextView or a subclass of TextView.
        // This isn't good alone, since if there are multiple TextViews, Espresso won't know which one to return --> Error
        // That's why we add the matcher that we want the parent to be toolbar

        // translates as:
        // 1. a view which is a TextView(or subclass)
        // 2. whose parent is a Toolbar(or subclass) / has ID R.id.action_bar
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                // withParent(withId(R.id.action_bar))))
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        )
            .check(matches(withText(R.string.app_name_sep)))


        // but when Google decides to change the view hierarchy, this test code won't make sense.
        // We need a different approach. By creating our own Matcher, which uses the parent Toolbar's method getTitle()
        onView(isAssignableFrom(Toolbar::class.java)).check(
            matches(
                withToolbarTitle(
                    appContext.getString(
                        R.string.app_name_sep
                    )
                )
            )
        )
    }

    fun withToolbarTitle(expectedTitle: CharSequence): Matcher<View> {

        // we want a BoundedMatcher, applying to the Toolbar class
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("with toolbar title: $expectedTitle")
            }

            override fun matchesSafely(toolbar: Toolbar?): Boolean {
                return expectedTitle == toolbar?.title
            }
        }

        // describeTo is called when the matching fails
    }


    @Test
    fun MainActivity_views_are_correctly_initialized() {
        val mainActivity: MainActivity = mainActivityTestRule.activity
        val searchImagesBtn: View = mainActivity.findViewById(R.id.btn_search_images)
        val randomImagesBtn: View = mainActivity.findViewById(R.id.btn_random_images)
        assertNotNull(searchImagesBtn)
        assertNotNull(randomImagesBtn)
        // assertThat1(searchImagesBtn, instanceOf(Button::class.java))
        // assertThat1(randomImagesBtn, instanceOf(Button::class.java))

        onView(withId(R.id.btn_search_images))
            .check(matches(withText(appContext.resources.getString(R.string.search_images))))
        onView(withId(R.id.btn_random_images))
            .check(matches(withText(appContext.resources.getString(R.string.find_a_random_image))))

    }


    @Test
    fun onClick_Button_starts_Activity() {
        val searchPicturesActivityMonitor: Instrumentation.ActivityMonitor =
            InstrumentationRegistry.getInstrumentation()
                .addMonitor(SearchPicturesActivity::class.simpleName, null, false)

        onView(withId(R.id.btn_search_images)).perform(click())

        val searchPicturesActivity: Activity? =
            searchPicturesActivityMonitor.waitForActivityWithTimeout(8 * 1000)
        assertNotNull("searchPicturesActivity not started after 10s", searchPicturesActivity)

    }
}
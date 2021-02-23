package com.asfartz.picturelistingapp

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewActivityTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule(ListViewActivity::class.java)

    @Test
    fun clickItem() {
        onView(withId(R.id.footer))
            .check(matches(not(isDisplayed())))

        onData(withValue(27))
            .inAdapterView(withId(R.id.list))
            .perform(click())

        onView(withId(R.id.footer))
            .check(matches(withText("27")))
            .check(matches(isDisplayed()))
    }

    // this BoundedMatcher does the following:
    //      -line 41: is looking for an item of type ListViewActivity.Item
    //      -line 42: describeTo: some text for when the test fails
    //      -line 46: matchesSafely: compare the item that Espresso is currenlty examining, with the value that we're looking for
    private fun withValue(value: Int): Matcher<Any> {
        return object : BoundedMatcher<Any, ListViewActivity.Item>(ListViewActivity.Item::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("has value $value")
            }

            override fun matchesSafely(item: ListViewActivity.Item?): Boolean {
                return item.toString() == value.toString()
            }

        }
    }
}
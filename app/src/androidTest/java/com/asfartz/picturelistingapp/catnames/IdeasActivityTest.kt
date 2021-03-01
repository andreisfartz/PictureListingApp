package com.asfartz.picturelistingapp.catnames

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.asfartz.picturelistingapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IdeasActivityTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule(IdeasActivity::class.java, true, false)

    @Test
    fun noTheme() {
        activityRule.launchActivity(null)

        onView(withId(R.id.theme_title))
            .check(matches(withText(R.string.missing_theme)))
    }

    @Test
    fun punnyTitleTheme() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val themeTitle = context.getString(R.string.theme_punny)

        val intent = Intent()
        intent.putExtra(IdeasActivity.KEY_THEME, themeTitle)
        activityRule.launchActivity(intent)

        onView(withId(R.id.theme_title))
            .check(matches(withText(themeTitle)))
    }

    @Test
    fun unknownTitleTheme() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val themeTitle = "Silly Billy"

        val intent = Intent()
        intent.putExtra(IdeasActivity.KEY_THEME, themeTitle)
        activityRule.launchActivity(intent)

        val message = context.getString(R.string.unknown_theme, themeTitle)
        onView(withId(R.id.theme_title))
            .check(matches(withText(message)))
    }
}
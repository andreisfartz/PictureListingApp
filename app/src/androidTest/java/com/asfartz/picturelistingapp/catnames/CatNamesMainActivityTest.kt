package com.asfartz.picturelistingapp.catnames

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.anyIntent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.asfartz.picturelistingapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatNamesMainActivityTest {

    // @Rule @JvmField
    // var activityRule = ActivityTestRule(CatNamesMainActivity::class.java)

    // IntentsTestRule extends ActivityTestRule
    @Rule @JvmField
    var intentRule = IntentsTestRule(CatNamesMainActivity::class.java)

    @Test
    fun punnyThemeTitle_result_by_launchingActivity() {
        onView(withId(R.id.button_punny))
            .perform(click())

        // check that the activity resulted from the button click, has the correct text in it's view
        onView(withId(R.id.theme_title))
            .check(matches(withText(R.string.theme_punny)))
    }

    @Test
    fun punnyThemeTitle_result_by_interceptingIntent() {
        onView(withId(R.id.button_punny))
            .perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)

        // check that the intercepted intent has the correct data
        Intents.intended(hasExtra(IdeasActivity.KEY_THEME, theme))

        // check that the intercepted intent has the correct Activity target.
        // First, use Intents.intended(anyIntent()), copy paste the anyIntent() method in this class,
        // and debug to see where exactly in the Intent object the class name is.
        // you'll see it's in ComponentName mComponent. So now we know what to test for.
        Intents.intended(IntentMatchers.hasComponent(
            ComponentNameMatchers.hasClassName("com.asfartz.picturelistingapp.catnames.IdeasActivity")
        ))

    }

    @Test
    fun punnyThemeTitle_onActivityResult_to_CatNamedMainActivity() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)

        val name = "Catalie Portman"
        val intent = Intent()
        intent.putExtra(IdeasActivity.KEY_NAME, name)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)

        // when the Intent with the (key,value) Extra listed below is intercepted, respond with this result
        // Note: the destination activity will not be launched.
        Intents.intending(hasExtra(IdeasActivity.KEY_THEME, theme)).respondWith(result)
        onView(withId(R.id.button_punny))
            .perform(click())

        onView((withId(R.id.name)))
            .check(matches(withText(name)))
    }



}
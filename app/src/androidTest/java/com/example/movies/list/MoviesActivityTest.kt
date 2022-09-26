package com.example.movies.list

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.example.movies.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MoviesActivityTest{
    @Before
    fun setup(){
        ActivityScenario.launch(MoviesActivity::class.java)
    }

    @Test
    fun profileTest(){
        onView(withId(R.id.action_profile)).check(matches(isDisplayed()))
        onView(withId(R.id.action_profile)).perform(click())
    }
}
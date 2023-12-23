package com.example.test_dagger_kotlin.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test_dagger_kotlin.R
import com.example.test_dagger_kotlin.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerView() {
//        val scenario = launchFragmentInContainer<HomeFragment>()
        try {
            // Sleep for 15 seconds
            Thread.sleep(15000)
            onView(withId(R.id.photo_recyclerView)).check(matches(isDisplayed()))
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

}
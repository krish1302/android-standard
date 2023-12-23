package com.example.test_dagger_kotlin.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.test_dagger_kotlin.R
import com.example.test_dagger_kotlin.databinding.ActivityMainBinding
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var binding: ActivityMainBinding

    @Before
    fun setUp() {
    }

    @Test
    fun testBottomNavigationView() {
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    }

//    @Test
//    fun testNavigationDashboard() {
//        onView(withId(R.id.navigation_dashboard)).perform(click())
//        onView(withId(R.id.fragment_dashboard)).check(matches(isDisplayed()))
//    }

}
package com.example.test_dagger_kotlin.viewmodel

import org.junit.jupiter.api.Tag
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(HomeViewModelTest::class, DashboardViewModelTest::class)
@Tag("ViewModelTest Group")
class ViewModelTest {
}
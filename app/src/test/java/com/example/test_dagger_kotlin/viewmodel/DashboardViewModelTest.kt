package com.example.test_dagger_kotlin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test_dagger_kotlin.room.dao.UserDao
import com.example.test_dagger_kotlin.room.entity.User
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Tag
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@Tag("DashboardViewModelTest")
class DashboardViewModelTest {

    // This rule swaps the background executor used by the Architecture Components with a different one
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    @RelaxedMockK
    lateinit var userDao : UserDao

    private lateinit var viewModel : DashboardViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        val listUser = listOf(User(1, "bala", "krishnan"))
        userDao = mockk {
            every { getAll() } returns listUser
        }
        viewModel =  DashboardViewModel(userDao)
    }


    @Test
    fun getUser() = runTest {
        // Define a list of User objects with some data
        val listUser = listOf(User(1, "bala", "krishnan"))

        // Call a method named getUsers() on a viewModel instance
        viewModel.getUsers()

        // Verify that a method named getAll() is called on a userDao instance
        verify { userDao.getAll() }

        // Advance the dispatcher scheduler until there are no more tasks in the queue
        dispatcher.scheduler.advanceUntilIdle()

        // Get the value of the getUser LiveData from the viewModel
        val result = viewModel.getUser.value

        // Assert that the result is equal to the previously defined listUser
        assertEquals(listUser, result)
    }

}
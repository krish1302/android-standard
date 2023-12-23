package com.example.test_dagger_kotlin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test_dagger_kotlin.models.Photos
import com.example.test_dagger_kotlin.repository.PhotosRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    // This rule swaps the background executor used by the Architecture Components with a different one
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var photosRepository: PhotosRepository
    private lateinit var homeViewModel: HomeViewModel

    private val dispatcher = StandardTestDispatcher()


    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        val responseBody = listOf(Photos(1,1, "title", "image.jpg", "image.jpg"))
        photosRepository = mockk {
            coEvery { getPhotos() } returns Response.success(responseBody)
        }
        homeViewModel = HomeViewModel(photosRepository)
    }

    @Tag("HomeViewModelTest")
    @Test
    fun testGetPhotosCorrect() = runTest {

        val photosList = listOf(Photos(1,1, "title", "image.jpg", "image.jpg"))

        homeViewModel.getPhotos()

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(photosList, homeViewModel.photos.value)
    }

    @Tag("HomeViewModelTest")
    @Test
    fun testGetPhotosWrong() = runTest {

        val photosList = listOf(Photos(1,1, "title", "image.jpg", "image1.jpg"))

        homeViewModel.getPhotos()

        dispatcher.scheduler.advanceUntilIdle()

        assertNotEquals(photosList, homeViewModel.photos.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

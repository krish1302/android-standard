package com.example.test_dagger_kotlin.repository

import com.example.test_dagger_kotlin.api.ApiInterface
import com.example.test_dagger_kotlin.models.Photos
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import retrofit2.Response


class PhotosRepositoryTest {

    private lateinit var apiInterface: ApiInterface
    private lateinit var photosRepository: PhotosRepository
    private val photos = listOf(Photos(1,1,"title", "url", "thumbnailUrl"))
    private val response = Response.success(photos)

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        apiInterface = mockk {
            coEvery { getPhotos() } returns response
        }
        photosRepository = PhotosRepository(apiInterface)
    }

    @Test
    fun getPhotos() = runTest {
        val result = photosRepository.getPhotos()

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(response, result)
    }
}
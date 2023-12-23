package com.example.test_dagger_kotlin.api

import com.example.test_dagger_kotlin.models.Photos
import com.example.test_dagger_kotlin.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(Constants.PHOTOS_GET_ENDPOINT)
    suspend fun getPhotos(): Response<List<Photos>>
}
package com.example.test_dagger_kotlin.repository

import com.example.test_dagger_kotlin.api.ApiInterface
import com.example.test_dagger_kotlin.models.Photos
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

class PhotosRepository
@Inject
constructor(private val apiInterface: ApiInterface){

    suspend fun getPhotos() : Response<List<Photos>> = apiInterface.getPhotos()
}
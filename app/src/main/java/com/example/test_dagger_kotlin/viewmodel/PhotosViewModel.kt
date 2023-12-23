package com.example.test_dagger_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_dagger_kotlin.models.Photos
import javax.inject.Inject

class PhotosViewModel
@Inject
constructor() : ViewModel (){

    private val _photos = MutableLiveData<Photos>()

    val photos: LiveData<Photos>
        get() = _photos
}
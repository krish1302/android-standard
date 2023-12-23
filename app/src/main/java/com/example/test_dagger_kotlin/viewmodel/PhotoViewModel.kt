package com.example.test_dagger_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_dagger_kotlin.models.Photos
import javax.inject.Inject

class PhotoViewModel
    @Inject
    constructor(): ViewModel() {

    private val _photo = MutableLiveData<Photos>()

    val photo: LiveData<Photos>
        get() = _photo

    fun setPhoto(photo: Photos) {
        _photo.value = photo
    }

}
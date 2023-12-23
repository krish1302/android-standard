package com.example.test_dagger_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_dagger_kotlin.models.Photos
import com.example.test_dagger_kotlin.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _photoResponse = MutableLiveData<List<Photos>>()

    val photos: LiveData<List<Photos>> = _photoResponse

    init {
        getPhotos()
    }

     fun getPhotos() = viewModelScope.launch {
        photosRepository.getPhotos().let {response ->
            if(response.isSuccessful){
                _photoResponse.apply {
                    value = response.body()
                }
            }
            else {
                Log.d("getPosts", "getPosts(): ${response.code()} ")
            }
        }
    }
}
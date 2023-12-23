package com.example.test_dagger_kotlin.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_dagger_kotlin.repository.PhotosRepository
import com.example.test_dagger_kotlin.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeViewModelFactory
@Inject
constructor(private val photosRepository: PhotosRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(photosRepository) as T
    }
}
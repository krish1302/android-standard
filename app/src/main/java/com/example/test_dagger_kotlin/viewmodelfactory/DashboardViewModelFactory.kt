package com.example.test_dagger_kotlin.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_dagger_kotlin.room.dao.UserDao
import com.example.test_dagger_kotlin.viewmodel.DashboardViewModel
import javax.inject.Inject

class DashboardViewModelFactory
@Inject
constructor(private val userDao: UserDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(userDao) as T
    }
}
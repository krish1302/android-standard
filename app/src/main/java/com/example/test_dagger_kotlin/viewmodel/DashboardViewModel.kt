package com.example.test_dagger_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_dagger_kotlin.room.dao.UserDao
import com.example.test_dagger_kotlin.room.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
    @Inject
    constructor(private val userDao: UserDao): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _user = MutableLiveData<List<User>>().apply {
        value = emptyList()
    }

    val getUser: LiveData<List<User>> = _user

    fun createUser() = CoroutineScope(Dispatchers.IO).launch {
        val user = User(1, "Balakrishnan", "Perumal")
        userDao.insertAll(user)
    }

    fun getUsers() = CoroutineScope(Dispatchers.IO).launch {
        val users = userDao.getAll()
        CoroutineScope(Dispatchers.Main).launch {
            _user.value = users
        }
    }
}
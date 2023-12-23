package com.example.test_dagger_kotlin.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_dagger_kotlin.room.dao.UserDao
import com.example.test_dagger_kotlin.room.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
package com.example.test_dagger_kotlin.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_dagger_kotlin.api.ApiInterface
import com.example.test_dagger_kotlin.repository.PhotosRepository
import com.example.test_dagger_kotlin.room.dao.UserDao
import com.example.test_dagger_kotlin.room.database.AppDatabase
import com.example.test_dagger_kotlin.utils.Constants
import com.example.test_dagger_kotlin.viewmodelfactory.DashboardViewModelFactory
import com.example.test_dagger_kotlin.viewmodelfactory.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DemoAppModule {

    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String) : ApiInterface =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun providePhotosRepository(apiInterface: ApiInterface) : PhotosRepository =
        PhotosRepository(apiInterface)

    @Provides
    @Singleton
    fun provideHomeViewModelFactory(photosRepository: PhotosRepository) : HomeViewModelFactory =
        HomeViewModelFactory(photosRepository)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context =
        application

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "room").build()

    @Provides
    @Singleton
    fun provideUserDao(roomDatabase: AppDatabase) : UserDao =
        roomDatabase.userDao()

    @Provides
    @Singleton
    fun provideDashboardViewModelFactory(userDao: UserDao) : DashboardViewModelFactory =
        DashboardViewModelFactory(userDao)


}
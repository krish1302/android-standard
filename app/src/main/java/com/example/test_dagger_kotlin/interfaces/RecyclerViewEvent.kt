package com.example.test_dagger_kotlin.interfaces

import com.example.test_dagger_kotlin.models.Photos

interface RecyclerViewEvent {
    fun onItemClick(position: Int, item: Photos)
}
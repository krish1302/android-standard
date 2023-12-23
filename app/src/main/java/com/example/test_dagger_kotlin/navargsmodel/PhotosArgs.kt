package com.example.test_dagger_kotlin.navargsmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotosArgs(val albumId: Int,
                      val id: Int,
                      val title: String,
                      val url: String,
                      val thumbnailUrl: String) : Parcelable

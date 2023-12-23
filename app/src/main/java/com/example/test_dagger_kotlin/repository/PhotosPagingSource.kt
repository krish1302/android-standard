package com.example.test_dagger_kotlin.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test_dagger_kotlin.api.ApiInterface
import com.example.test_dagger_kotlin.models.Photos
import com.example.test_dagger_kotlin.utils.Constants

class PhotosPagingSource (private val apiInterface: ApiInterface) : PagingSource<Int, Photos>() {
    override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val page = params.key ?: Constants.STARTING_PAGE_INDEX
        TODO("Not yet implemented")
    }
}
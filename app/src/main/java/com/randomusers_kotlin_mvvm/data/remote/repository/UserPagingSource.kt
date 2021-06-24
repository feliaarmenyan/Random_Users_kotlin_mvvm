package com.randomusers_kotlin_mvvm.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.randomusers_kotlin_mvvm.data.remote.api.RandomUsersApiService
import com.randomusers_kotlin_mvvm.data.remote.model.User

class UserPagingSource(
    private val usersApi: RandomUsersApiService,
) :
    PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val startFrom = params.key ?: 0
            val response =
                usersApi.getRandomUsers(
                    page = startFrom,
                    results = 20
                ).results

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.size < 20) null else startFrom + 20
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}
package com.randomusers_kotlin_mvvm.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.randomusers_kotlin_mvvm.data.UseCaseResult
import com.randomusers_kotlin_mvvm.data.remote.api.RandomUsersApiService
import com.randomusers_kotlin_mvvm.data.remote.model.RandomUsers
import com.randomusers_kotlin_mvvm.data.remote.model.User
import kotlinx.coroutines.flow.Flow

interface RandomUsersRepository {
    fun getRandomUsersPaging(): Flow<PagingData<User>>
}

class RandomUsersRepositoryImpl(private val randomUsersApi: RandomUsersApiService) :
    RandomUsersRepository {

    override fun getRandomUsersPaging() = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
    ) {
        UserPagingSource(
            usersApi = randomUsersApi
        )
    }.flow
}
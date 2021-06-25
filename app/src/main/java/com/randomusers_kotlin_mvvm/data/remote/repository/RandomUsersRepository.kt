package com.randomusers_kotlin_mvvm.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.randomusers_kotlin_mvvm.data.local.db.dao.UserDao
import com.randomusers_kotlin_mvvm.data.remote.api.RandomUsersApiService
import com.randomusers_kotlin_mvvm.data.remote.model.User
import kotlinx.coroutines.flow.Flow

interface RandomUsersRepository {
    fun getRandomUsersPaging(): Flow<PagingData<User>>
}

class RandomUsersRepositoryImpl(
    private val randomUsersApi: RandomUsersApiService, private val dao: UserDao
) :
    RandomUsersRepository {

    override fun getRandomUsersPaging() = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
    ) {
        UserPagingSource(
            usersApi = randomUsersApi,
            dao = dao
        )
    }.flow
}
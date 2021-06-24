package com.randomusers_kotlin_mvvm.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.randomusers_kotlin_mvvm.core.platform.BaseViewModel
import com.randomusers_kotlin_mvvm.data.remote.repository.RandomUsersRepository

class RandomUsersViewModel(private val repository: RandomUsersRepository) : BaseViewModel() {

    val users =
        repository.getRandomUsersPaging()
            .cachedIn(viewModelScope)

}
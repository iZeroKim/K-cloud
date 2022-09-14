package com.wasusi.k_cloud.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasusi.k_cloud.data.repositories.FoldersRepository
import com.wasusi.k_cloud.data.repositories.UserRepository

class HomeViewModelFactory(
    private val repository: UserRepository, private val foldersRepository: FoldersRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository, foldersRepository) as T
    }
}
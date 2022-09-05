package com.wasusi.k_cloud.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasusi.k_cloud.data.repositories.UserRepository

class AuthViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}
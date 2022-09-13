package com.wasusi.k_cloud.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.wasusi.k_cloud.data.repositories.FoldersRepository
import com.wasusi.k_cloud.data.repositories.UserRepository
import com.wasusi.k_cloud.util.startLoginActivity
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val repository: UserRepository,
    private val foldersRepository: FoldersRepository
): ViewModel(){
    private val disposables = CompositeDisposable()


    val user by lazy{
        repository.currentUser()
    }

    fun fetchFolders(){
        foldersRepository.fetchFolders()
    }

    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()

    }

}
package com.wasusi.k_cloud.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wasusi.k_cloud.data.models.Folder
import com.wasusi.k_cloud.data.repositories.FoldersRepository
import com.wasusi.k_cloud.data.repositories.UserRepository
import com.wasusi.k_cloud.util.startLoginActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository,
    private val foldersRepository: FoldersRepository
): ViewModel(){
    private val disposables = CompositeDisposable()
    private val _folders = MutableLiveData<List<Folder>>()
    val folders: LiveData<List<Folder>> = _folders

    val user by lazy{
        repository.currentUser()
    }
    init {
        viewModelScope.launch {
            _folders.value = foldersRepository.fetchFolders(user!!.uid)
        }
    }





    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()

    }

}
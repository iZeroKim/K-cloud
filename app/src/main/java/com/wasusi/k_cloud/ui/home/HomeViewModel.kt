package com.wasusi.k_cloud.ui.home

import android.util.Log
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

 val folder = Folder("", "files", "25/04/2024")
    val user by lazy{
        repository.currentUser()
    }
    init {
        viewModelScope.launch {
            Log.i("Folders", foldersRepository.fetchFolders(user!!.uid).toString())
            _folders.value = foldersRepository.fetchFolders(user!!.uid)
        }
    }

    fun insertFolder(){
        Log.i("here", "isk")
        viewModelScope.launch {
            foldersRepository.insertFolder(folder)
        }

    }



    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()

    }

}
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
    private val _folder_count = MutableLiveData<Int>()
    val folders: LiveData<List<Folder>> = _folders
    var folder_count = _folder_count

    val user by lazy{
        repository.currentUser()
    }
    init {
        viewModelScope.launch {
            Log.i("Folders", foldersRepository.fetchFolders(user!!.uid).toString())
            _folder_count.value = foldersRepository.fetchFolders(user!!.uid).size
            _folders.value = foldersRepository.fetchFolders(user!!.uid)
        }
    }

    fun insertFolder(name: String, currentDate: String) {
        viewModelScope.launch {
            foldersRepository.insertFolder(Folder(currentDate + name, name, currentDate))
        }

    }



    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()

    }

}
package com.wasusi.k_cloud.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.wasusi.k_cloud.data.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    //Navigate to Register Activity
    fun gotToRegister(view: View) {
        Intent(view.context, RegisterActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    //Navigate to Login Activity
    fun gotToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    //Register
    fun register() {
        //Validate email
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        //Start authentication

        authListener?.onStarted()

        //Perform register on repository

        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { authListener?.onSuccess() },
                { authListener?.onFailure(it.message!!) }
            )

        disposables.add(disposable)
    }

    //Login
    fun login() {
        //Validate email
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        //Start authentication
        authListener?.onStarted()

        //Perform login on repository

        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { authListener?.onSuccess() },
                { authListener?.onFailure(it.message!!) }
            )

        disposables.add(disposable)
    }


    // Dispose disposables
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}
package com.wasusi.k_cloud.data.repositories

import com.wasusi.k_cloud.data.firebase.Firebase

class UserRepository(
    private val firebase: Firebase
) {
    fun register(email: String, password: String){
        firebase.register(email, password)
    }

    fun login(email: String, password: String){
        firebase.login(email, password)
    }

    fun logout(){
        firebase.logout()
    }

    fun currentUser(){
        firebase.currentUser()
    }
}
package com.wasusi.k_cloud.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable

class Firebase {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val db by lazy{
        Firebase.firestore
    }

    fun login(email: String, password: String) = Completable.create{emitter ->
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(!emitter.isDisposed){
                if(it.isSuccessful){
                    emitter.onComplete()
                } else{
                    emitter.onError(it.exception!!)
                }
            }
        }
    }

    fun register(email: String, password: String) = Completable.create{emitter ->
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(!emitter.isDisposed){
                if(it.isSuccessful){
                    emitter.onComplete()
                } else{
                    emitter.onError(it.exception!!)
                }
            }
        }
    }

    fun logout() = auth.signOut()

    fun currentUser() = auth.currentUser

    fun getFolders() = Completable.create{emitter ->
        db.collection("folders")
            .get()
            .addOnCompleteListener {
                if(!emitter.isDisposed){
                    if(it.isSuccessful){
                        emitter.onComplete()
                    } else{
                        emitter.onError(it.exception!!)
                    }
                }
            }

    }
}
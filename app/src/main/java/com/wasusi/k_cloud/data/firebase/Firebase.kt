package com.wasusi.k_cloud.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wasusi.k_cloud.data.models.Folder
import com.wasusi.k_cloud.data.models.Folder.Companion.toFolder
import io.reactivex.Completable
import kotlinx.coroutines.tasks.await

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

    suspend fun insertFolder(folder: Folder): Boolean{
        return try {
            db.collection("users").document().set(folder).await()
            return true
        } catch (e:Exception){
            Log.e("Folders", "Error inserting folder",e )
            return false
        }
    }

    suspend fun getFolders(userId: String): List<Folder>{
        return try {
            db.collection("users")
                .document(userId)
                .collection("folders").get().await()
                .documents.mapNotNull {
                    it.toFolder()
                }
        } catch (e: Exception){
            Log.e("Folders", "Error fetching folders",e )
            emptyList<Folder>()
        }
    }


}
package com.wasusi.k_cloud.data.models

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import java.io.Serializable


data class Folder(
    val folderId:String,
    val name:String,
    val created_at:String
): Serializable{
    companion object{
        fun DocumentSnapshot.toFolder(): Folder?{
            try{
                val name = getString("name")!!
                val created_at = getString("created_at")!!
                return Folder(id, name, created_at)
            } catch (e: Exception){
                Log.e("Folder", "Error converting fetched folders", e)
                return null
            }
        }
    }

}

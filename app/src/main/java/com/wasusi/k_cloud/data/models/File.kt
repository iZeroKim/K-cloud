package com.wasusi.k_cloud.data.models

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import java.io.Serializable

data class File(
    val fileId:String,
    val name:String,
    val imageUri: String,
    val created_at:String
): Serializable {
    companion object{
        fun DocumentSnapshot.toFile(): File?{
            try{
                val name = getString("name")!!
                val imageUri = getString("imageUri")!!
                val created_at = getString("created_at")!!
                return File(id, name, imageUri, created_at)
            } catch (e: Exception){
                Log.e("Files", "Error converting fetched files", e)
                return null
            }
        }
    }
}

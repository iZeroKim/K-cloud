package com.wasusi.k_cloud.data.repositories

import com.wasusi.k_cloud.data.firebase.Firebase
import com.wasusi.k_cloud.data.models.Folder

class FoldersRepository(
    private val firebase: Firebase
) {
    suspend fun fetchFolders(userId: String): List<Folder> = firebase.getFolders(userId)
}
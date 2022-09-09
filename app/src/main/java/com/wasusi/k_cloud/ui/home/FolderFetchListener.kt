package com.wasusi.k_cloud.ui.home

interface FolderFetchListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}
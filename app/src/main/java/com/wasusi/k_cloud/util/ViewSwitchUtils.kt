package com.wasusi.k_cloud.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.wasusi.k_cloud.ui.auth.LoginActivity
import com.wasusi.k_cloud.ui.files.FileDetailsActivity
import com.wasusi.k_cloud.ui.folders.FolderDetailsActivity
import com.wasusi.k_cloud.ui.home.MainActivity

fun Context.startHomeActivity() =
    Intent(this, MainActivity::class.java).also{
        Log.i("position", "Lastly hee")
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also{
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startFolderDetailsActivity(bundle: Bundle) =
    Intent(this, FolderDetailsActivity::class.java).also {
        startActivity(it)
    }
fun Context.startFileDetailsActivity(bundle: Bundle) =
    Intent(this, FileDetailsActivity::class.java).also {
        startActivity(it)
    }
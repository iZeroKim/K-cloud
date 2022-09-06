package com.wasusi.k_cloud.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.wasusi.k_cloud.ui.auth.LoginActivity
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
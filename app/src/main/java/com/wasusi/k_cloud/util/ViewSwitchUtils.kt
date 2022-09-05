package com.wasusi.k_cloud.util

import android.content.Context
import android.content.Intent
import com.wasusi.k_cloud.ui.auth.LoginActivity
import com.wasusi.k_cloud.ui.home.MainActivity

class ViewSwitchUtils {
    fun Context.startHomeActivity() =
        Intent(this, MainActivity::class.java).also{
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

    fun Context.startLoginActivity() =
        Intent(this, LoginActivity::class.java).also{
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
}
package com.wasusi.k_cloud.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult

fun Context.fetchFile(type: String) {
    val REQUEST_CODE = 100

    val intent = Intent(Intent.ACTION_PICK)
    intent.type = type
    startActivityForResult(this as Activity, intent, REQUEST_CODE, null)
}

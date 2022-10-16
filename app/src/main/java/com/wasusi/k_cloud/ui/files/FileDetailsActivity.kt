package com.wasusi.k_cloud.ui.files

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wasusi.k_cloud.R

class FileDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Kcloud)
        setContentView(R.layout.activity_files_details)
    }
}
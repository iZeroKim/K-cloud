package com.wasusi.k_cloud.ui.folders

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.databinding.ActivityFolderDetailsBinding

class FolderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Kcloud)
        val binding: ActivityFolderDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_folder_details)
        // my_child_toolbar is defined in the layout file
        setSupportActionBar(binding.toolbar2)

        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var REQUEST_CODE = 100
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            // handle chosen image
            Log.i("uri", data?.data.toString())
        }
    }

}
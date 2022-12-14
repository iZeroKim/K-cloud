package com.wasusi.k_cloud.ui.folders

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.data.models.Folder
import com.wasusi.k_cloud.databinding.ActivityFolderDetailsBinding
import com.wasusi.k_cloud.ui.home.MainActivity
import com.wasusi.k_cloud.util.adapters.MonthsAdapter
import com.wasusi.k_cloud.util.backTo
import com.wasusi.k_cloud.util.showFileAddDialog

class FolderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Kcloud)
        val binding: ActivityFolderDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_folder_details)

        setSupportActionBar(binding.toolbar2)

        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val folder = intent.extras?.get("current_folder") as Folder
        binding.folderName.text = folder.name
        binding.addFile.setOnClickListener {
            showFileAddDialog(this)
        }
        binding.toolbar2.setNavigationOnClickListener {
            this.backTo(MainActivity())
        }

        val exampleMonth = ArrayList<String>()

        exampleMonth.add("May 2022")
        exampleMonth.add("Jun 2022")
        exampleMonth.add("Oct 2022")
        exampleMonth.add("Dec 2022")

        val rvFiles = binding.rvFiles
        val adapter = MonthsAdapter(exampleMonth)
        rvFiles.adapter = adapter




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var REQUEST_CODE = 100
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            // handle chosen image
            Log.i("uri", data?.data.toString())
        }
    }


}
package com.wasusi.k_cloud.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.databinding.ActivityMainBinding
import com.wasusi.k_cloud.util.adapters.FoldersAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val homeViewModelFactory: HomeViewModelFactory by instance()

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)


        binding.viewmodel = homeViewModel

        val recyclerView = binding.rvfolders
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.nav_menu)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        homeViewModel.folders.observe(this, Observer {
            if(!it.isEmpty()){
                recyclerView.adapter = FoldersAdapter(it)
                recyclerView.visibility = View.VISIBLE
                binding.emptyList.visibility = View.GONE

            }
        })

        homeViewModel.folder_count.observe(this, Observer {
            binding.folderCount.text = it.toString()
        })

        /**
         * Show BottomSheetDialog
         * */
        binding.cardAddFolder.setOnClickListener {
            showBottomSheetDialog(homeViewModel)

        }

    }

    private fun showBottomSheetDialog(homeViewModel: HomeViewModel) {
        val dialog = BottomSheetDialog(this)
        val btmSheetView = layoutInflater.inflate(R.layout.add_folder_layout, null, false)
        dialog.setContentView(btmSheetView)
        dialog.setCancelable(true)

        val btnCancel = btmSheetView.findViewById<ImageView>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        val btn_add_folder = btmSheetView.findViewById<Button>(R.id.btnAddFolder)
        btn_add_folder.setOnClickListener {
            val name = btmSheetView.findViewById<EditText>(R.id.etName).text.toString()

            /**
             * Save folder
             * */
            var calendar: Calendar? = Calendar.getInstance()
            val date: Date = calendar!!.getTime()
            val currentDate = "${SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime())}, ${date}"

            homeViewModel.insertFolder(name , currentDate)
            dialog.dismiss()
        }

        dialog.show()
    }
}
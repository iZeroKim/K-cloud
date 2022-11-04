package com.wasusi.k_cloud.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.databinding.ActivityMainBinding
import com.wasusi.k_cloud.ui.auth.AuthViewModel
import com.wasusi.k_cloud.ui.auth.AuthViewModelFactory
import com.wasusi.k_cloud.util.adapters.FoldersAdapter
import com.wasusi.k_cloud.util.network.connectionStatusConnected
import com.wasusi.k_cloud.util.startLoginActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by kodein()
    private val homeViewModelFactory: HomeViewModelFactory by instance()
    private val authViewModelFactory:AuthViewModelFactory by instance()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Kcloud)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
        authViewModel = ViewModelProvider(this, authViewModelFactory).get(AuthViewModel::class.java)

        binding.viewmodel = homeViewModel

        val recyclerView = binding.rvfolders
        var adapter: FoldersAdapter? = null
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.nav_menu)
        supportActionBar?.setDisplayUseLogoEnabled(true)


        homeViewModel.folders.observe(this, Observer {
            if(!it.isEmpty()){

                adapter = FoldersAdapter(it)
                recyclerView.adapter = adapter!!
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
            showBottomSheetDialog(homeViewModel, adapter!!)
            adapter!!.notifyDataSetChanged()
        }

    }

    override fun onStart() {
        super.onStart()
        authViewModel.user?.let {
            startLoginActivity()
        }
    }



    private fun showBottomSheetDialog(homeViewModel: HomeViewModel, adapter: FoldersAdapter) {
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

            if(!this.connectionStatusConnected()){
                Toast.makeText(this, "No internet Connection", Toast.LENGTH_SHORT).show()
                Log.i("add", "No internet ")
            } else{
                homeViewModel.insertFolder(name , currentDate)
                adapter.notifyDataSetChanged()
                Log.i("add", "Yes internet")
                dialog.dismiss()
            }

        }

        dialog.show()
    }
}
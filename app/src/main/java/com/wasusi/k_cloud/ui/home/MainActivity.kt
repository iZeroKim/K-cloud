package com.wasusi.k_cloud.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.databinding.ActivityMainBinding
import com.wasusi.k_cloud.util.adapters.FoldersAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

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

    }
}
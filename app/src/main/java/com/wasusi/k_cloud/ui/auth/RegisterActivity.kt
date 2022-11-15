package com.wasusi.k_cloud.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.databinding.ActivityRegisterBinding
import com.wasusi.k_cloud.util.startHomeActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RegisterActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein: Kodein by kodein()
    private val authViewModelFactory: AuthViewModelFactory by instance()

    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Kcloud)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        authViewModel = ViewModelProvider(this, authViewModelFactory).get(AuthViewModel::class.java)
        binding.viewmodel = authViewModel

        authViewModel.authListener = this
    }


    override fun onStarted() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        binding.progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        binding.progressbar.visibility = View.GONE
        Log.i("Register", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
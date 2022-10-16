package com.wasusi.k_cloud.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.wasusi.k_cloud.R
import com.wasusi.k_cloud.databinding.ActivityLoginBinding
import com.wasusi.k_cloud.util.startHomeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware, AuthListener {
    override val kodein by kodein()
    private val authViewModelFactory: AuthViewModelFactory by instance()

    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Kcloud)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        authViewModel = ViewModelProvider(this, authViewModelFactory).get(AuthViewModel::class.java)
        binding.viewmodel = authViewModel

        authViewModel.authListener = this
    }

    override fun onStart() {
        super.onStart()
        Log.i("position", "Here")
        authViewModel.user?.let {
            startHomeActivity()
        }
    }

    override fun onStarted() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        binding.progressbar.visibility = View.GONE
        Log.i("position", "Now here")
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        binding.progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
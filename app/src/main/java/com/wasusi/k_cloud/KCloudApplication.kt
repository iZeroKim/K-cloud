package com.wasusi.k_cloud

import android.app.Application
import com.wasusi.k_cloud.data.firebase.Firebase
import com.wasusi.k_cloud.data.repositories.FoldersRepository
import com.wasusi.k_cloud.data.repositories.UserRepository
import com.wasusi.k_cloud.ui.auth.AuthViewModelFactory
import com.wasusi.k_cloud.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class KCloudApplication : Application(), KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@KCloudApplication))
        bind() from singleton { Firebase() }
        bind() from singleton { UserRepository(instance()) }
        bind() from singleton { FoldersRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance(), instance()) }
    }

}
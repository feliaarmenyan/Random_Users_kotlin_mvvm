package com.randomusers_kotlin_mvvm

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.randomusers_kotlin_mvvm.di.appModule
import com.randomusers_kotlin_mvvm.di.networkModule
import com.randomusers_kotlin_mvvm.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class RandomUsersApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Adding Koin modules to our application
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@RandomUsersApplication)
            modules(listOf(appModule, networkModule, viewModelsModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

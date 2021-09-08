package com.wasambo.news

import android.app.Application
import com.wasambo.news.di.RetrofitModule
import com.wasambo.news.di.apiModule
import com.wasambo.news.di.repositoryModule
import com.wasambo.news.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {
    companion object {
        lateinit var instance: MainApp
            private set
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApp)
            modules(
                listOf(
                    viewModelModule,
                    apiModule,
                    repositoryModule,
                    RetrofitModule
                )
            )
        }
        instance = this
    }
}

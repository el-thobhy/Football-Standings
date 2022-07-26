package com.elthobhy.footballklasemen

import android.app.Application
import android.content.ContentValues
import android.util.Log
import com.elthobhy.footballklasemen.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e(ContentValues.TAG, "onCreate: MyApp", )
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                networking,
                databaseModule,
                repositoryModule,
                viewModelModule,
                adapterModule
            )
        }
    }
}
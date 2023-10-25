package com.myapp.ccounter.android

import android.app.Application
import com.myapp.ccounter.android.di.appModule
import com.myapp.ccounter.di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CCounter: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CCounter)
            modules(appModule + getSharedModules())
        }
    }
}

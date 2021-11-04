package com.example.gbapp6

import android.app.Application
import com.example.gbapp6.di.DI
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                DI.getModule()
            )
        }
    }

}

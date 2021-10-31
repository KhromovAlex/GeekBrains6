package com.example.gbapp6

import com.example.gbapp6.di.DaggerAppComponent
import com.example.gbapp6.scheduler.DefaultSchedulers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerAppComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(DefaultSchedulers())
            .build()

}

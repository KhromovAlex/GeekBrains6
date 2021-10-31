package com.example.gbapp6.di

import android.content.Context
import com.example.gbapp6.scheduler.Schedulers
import com.example.gbapp6.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, DictionaryModule::class, DictionaryApiModule::class, CommonModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): AppComponent

    }

}

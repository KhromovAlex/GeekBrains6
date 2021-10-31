package com.example.gbapp6.di

import com.example.gbapp6.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CommonModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

}

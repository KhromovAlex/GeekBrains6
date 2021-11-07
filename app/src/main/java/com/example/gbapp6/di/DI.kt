package com.example.gbapp6.di

import com.example.gbapp6.domain.repository.StopwatchRepository
import com.example.gbapp6.domain.repository.StopwatchRepositoryImpl
import com.example.gbapp6.presentation.dictionary.StopwatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DI {

    fun getModule() = module {

        single<StopwatchRepository> { StopwatchRepositoryImpl() }

        viewModel { StopwatchViewModel(stopwatchRepository = get()) }

    }

}

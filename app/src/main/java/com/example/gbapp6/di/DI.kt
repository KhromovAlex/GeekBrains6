package com.example.gbapp6.di

import com.example.gbapp6.data.api.DictionaryApi
import com.example.gbapp6.data.datasource.DictionaryDataSource
import com.example.gbapp6.data.datasource.DictionaryDataSourceImpl
import com.example.gbapp6.domain.repository.DictionaryRepository
import com.example.gbapp6.domain.repository.DictionaryRepositoryImpl
import com.example.gbapp6.presentation.dictionary.DictionaryListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DI {

    fun getModule() = module {

        single<DictionaryApi> {
            Retrofit.Builder()
                .baseUrl("https://dictionary.skyeng.ru/")
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DictionaryApi::class.java)
        }

        single<DictionaryDataSource> { DictionaryDataSourceImpl(dictionaryApi = get()) }

        single<DictionaryRepository> { DictionaryRepositoryImpl(dictionaryDataSource = get()) }

        viewModel { DictionaryListViewModel(dictionaryRepository = get()) }

    }

}

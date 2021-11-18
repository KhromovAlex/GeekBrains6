package com.example.gbapp6.di

import androidx.room.Room
import com.example.database.DataBase
import com.example.gbapp6.data.api.DictionaryApi
import com.example.gbapp6.data.datasource.DictionaryLocalDataSource
import com.example.gbapp6.data.datasource.DictionaryLocalDataSourceImpl
import com.example.gbapp6.data.datasource.DictionaryRemoteDataSource
import com.example.gbapp6.data.datasource.DictionaryRemoteDataSourceImpl
import com.example.gbapp6.domain.repository.DictionaryRepository
import com.example.gbapp6.domain.repository.DictionaryRepositoryImpl
import com.example.gbapp6.presentation.dictionary.DictionaryListViewModel
import com.example.gbapp6.presentation.history.HistoryListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DI {

    private const val BASE_URL = "https://dictionary.skyeng.ru/"

    fun getModule() = module {

        single {
            Room
                .databaseBuilder(androidContext(), DataBase::class.java, "app_db")
                .build()
        }

        single<DictionaryApi> {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
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

        single<DictionaryRemoteDataSource> { DictionaryRemoteDataSourceImpl(dictionaryApi = get()) }

        single<DictionaryLocalDataSource> { DictionaryLocalDataSourceImpl(dataBase = get()) }

        single<DictionaryRepository> {
            DictionaryRepositoryImpl(
                dictionaryRemoteDataSource = get(),
                dictionaryLocalDataSource = get()
            )
        }

        viewModel { DictionaryListViewModel(dictionaryRepository = get()) }

        viewModel { HistoryListViewModel(dictionaryRepository = get()) }

    }

}

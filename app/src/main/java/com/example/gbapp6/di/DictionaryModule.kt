package com.example.gbapp6.di

import com.example.gbapp6.data.datasource.DictionaryDataSource
import com.example.gbapp6.data.datasource.DictionaryDataSourceImpl
import com.example.gbapp6.domain.repository.DictionaryRepository
import com.example.gbapp6.domain.repository.DictionaryRepositoryImpl
import com.example.gbapp6.presentation.dictionary.DictionaryListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface DictionaryModule {

    @ContributesAndroidInjector
    fun bindDictionaryListFragment(): DictionaryListFragment

    @Binds
    fun bindDictionaryDataSource(dictionaryDataSourceImpl: DictionaryDataSourceImpl): DictionaryDataSource

    @Binds
    fun bindDictionaryRepository(dictionaryRepositoryImpl: DictionaryRepositoryImpl): DictionaryRepository

}

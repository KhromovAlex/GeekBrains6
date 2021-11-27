package com.example.gbapp6

import com.example.gbapp6.data.datasource.DictionaryRemoteDataSource
import com.example.gbapp6.di.DI.getModule
import com.example.gbapp6.domain.entity.DataModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

class DictionaryRemoteDataSourceTest : KoinTest {
    private val dictionaryRemoteDataSource: DictionaryRemoteDataSource by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(getModule())
    }

    @Test
    fun assertNotEquals() = runBlocking {
        val data = dictionaryRemoteDataSource.getData("hello")

        assertNotEquals(data, listOf<DataModel>())
    }

    @Test
    fun assertNotNull() = runBlocking {
        val data = dictionaryRemoteDataSource.getData("hello")

        assertNotNull(data)
    }
}

package com.example.gbapp6.data.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersProviderTest : DispatchersProvider {
    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun background(): CoroutineDispatcher = Dispatchers.Main
}

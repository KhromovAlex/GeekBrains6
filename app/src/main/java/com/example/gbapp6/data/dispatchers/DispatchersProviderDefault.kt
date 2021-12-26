package com.example.gbapp6.data.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersProviderDefault : DispatchersProvider {
    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun background(): CoroutineDispatcher = Dispatchers.IO
}

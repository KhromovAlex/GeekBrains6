package com.example.gbapp6.data.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    fun main(): CoroutineDispatcher
    fun background(): CoroutineDispatcher
}

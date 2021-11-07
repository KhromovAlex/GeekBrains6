package com.example.gbapp6.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchRepositoryImpl : StopwatchRepository {
    private val mutableTicker = MutableStateFlow(0L)

    override suspend fun updateTicker(delay: Long) {
        mutableTicker.value = mutableTicker.value + delay
    }

    override suspend fun getTicker(): StateFlow<Long> = mutableTicker

    override suspend fun clearTicker() {
        mutableTicker.value = 0L
    }

}

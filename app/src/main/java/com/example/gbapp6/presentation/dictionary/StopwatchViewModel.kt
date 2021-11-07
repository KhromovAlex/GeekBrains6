package com.example.gbapp6.presentation.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gbapp6.domain.entity.AppState
import com.example.gbapp6.domain.repository.StopwatchRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class StopwatchViewModel(
    private val stopwatchRepository: StopwatchRepository,
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var jobStart: Job? = null
    private var jobStop: Job? = null
    private var jobWatch: Job? = null
    private val delay: Long = 20L

    private val _liveData = MutableLiveData<AppState<Long>>()
    val liveData: LiveData<AppState<Long>> = _liveData

    fun start() {
        if (jobStart == null) {
            jobStart = scope.launch {
                while (isActive) {
                    delay(delay)
                    stopwatchRepository.updateTicker(delay)
                }
            }
        }
    }

    fun pause() {
        jobStart?.cancel()
        jobStart = null
    }

    fun stop() {
        jobStart?.cancel()
        jobStart = null

        jobStop?.cancel()
        jobStop = scope.launch {
            stopwatchRepository.clearTicker()
        }
    }

    fun watch() {
        jobWatch?.cancel()
        jobWatch = scope.launch {
            stopwatchRepository.getTicker().collect { _liveData.postValue(AppState.Success(it)) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}

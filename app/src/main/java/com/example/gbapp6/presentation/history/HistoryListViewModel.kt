package com.example.gbapp6.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.gbapp6.domain.entity.AppState
import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.domain.repository.DictionaryRepository
import kotlinx.coroutines.*

class HistoryListViewModel(
    private val dictionaryRepository: DictionaryRepository,
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    private val _liveData = MutableLiveData<AppState<DataModel>>()
    val liveData: LiveData<AppState<DataModel>> = _liveData

    fun searchData(word: String) {
        job?.cancel()
        job = scope.launch {
            try {
                val data = dictionaryRepository
                    .getHistoryByText(word)
                _liveData.postValue(AppState.Success(data))
            } catch (e: Throwable) {
                _liveData.postValue(AppState.Error(e))
            }

        }

    }

    fun clearAppState() {
        _liveData.postValue(AppState.Empty())
    }

    fun getData():
            LiveData<AppState<List<DataModel>>> {
        return Transformations.map(
            dictionaryRepository
                .getAllHistory()
        ) { AppState.Success(it) }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}

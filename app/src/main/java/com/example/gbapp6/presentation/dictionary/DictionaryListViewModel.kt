package com.example.gbapp6.presentation.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gbapp6.domain.entity.AppState
import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.domain.repository.DictionaryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DictionaryListViewModel(
    private val dictionaryRepository: DictionaryRepository,
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    private val _liveData = MutableLiveData<AppState<List<DataModel>>>()
    val liveData: LiveData<AppState<List<DataModel>>> = _liveData

    fun getData(word: String) {
        job?.cancel()
        job = scope.launch {
            try {
                val data = dictionaryRepository
                    .getData(word)
                _liveData.postValue(AppState.Success(data))
            } catch (e: Throwable) {
                _liveData.postValue(AppState.Error(e))
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}

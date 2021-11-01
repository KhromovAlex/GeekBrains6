package com.example.gbapp6.presentation.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gbapp6.domain.entity.AppState
import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.domain.repository.DictionaryRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

class DictionaryListViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
    private val schedulers: com.example.gbapp6.scheduler.Schedulers
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _liveData = MutableLiveData<AppState<List<DataModel>>>()
    val liveData: LiveData<AppState<List<DataModel>>> = _liveData

    fun getData(word: String) {
        disposable +=
            dictionaryRepository
                .getData(word)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    { _liveData.postValue(AppState.Success(it)) },
                    { _liveData.postValue(AppState.Error(it)) }
                )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

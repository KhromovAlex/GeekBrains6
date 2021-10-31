package com.example.gbapp6.presentation.dictionary

import com.example.gbapp6.domain.repository.DictionaryRepository
import com.example.gbapp6.presentation.common.Presenter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

class DictionaryPresenter @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
    private val schedulers: com.example.gbapp6.scheduler.Schedulers,
) : Presenter<DictionaryListView> {
    private var dictionaryView: DictionaryListView? = null
    private val disposable = CompositeDisposable()

    override fun attachView(view: DictionaryListView) {
        dictionaryView = view
    }

    override fun detachView(view: DictionaryListView) {
        dictionaryView = null

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    fun getData(word: String) {
        if (dictionaryView == null) return

        disposable +=
            dictionaryRepository
                .getData(word)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    dictionaryView!!::renderList,
                    dictionaryView!!::renderError
                )
    }

}

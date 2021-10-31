package com.example.gbapp6.presentation.common

interface Presenter<T: View> {

    fun attachView(view: T)

    fun detachView(view: T)

    fun onDestroy() {}

}

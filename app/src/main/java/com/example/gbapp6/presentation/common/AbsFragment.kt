package com.example.gbapp6.presentation.common

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class AbsFragment<V: View, T : Presenter<V>>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId),
    HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private var presenter: T? = null

    fun getPresenterOrNull(): T? = presenter

    abstract fun createPresenter(): T

    abstract fun getCurrentView(): V

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        presenter = createPresenter()
        presenter?.attachView(getCurrentView())
    }

    override fun onDestroyView() {
        presenter?.detachView(getCurrentView())
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}

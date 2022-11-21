package by.kmmq.minz.utils

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


open class RxViewModel : ViewModel() {

    private val lifecycleDisposables = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        lifecycleDisposables.clear()
    }

    fun Disposable.bindToLifecycle(): Disposable {
        lifecycleDisposables.add(this)
        return this
    }

}
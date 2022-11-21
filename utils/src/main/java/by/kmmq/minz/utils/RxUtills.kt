package by.kmmq.minz.utils

import androidx.lifecycle.*
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.disposeOnDestroyLifecycle(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                this@disposeOnDestroyLifecycle.dispose()
            }
        }
    })
}

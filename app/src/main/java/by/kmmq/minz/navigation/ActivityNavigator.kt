package by.kmmq.minz.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator

class ActivityNavigator<T : AppCompatActivity>(
    private val navigatorHolder: NavigatorHolder,
    @IdRes private val appContainerId: Int
) : INavigator<T> {

    override fun attach(subject: T) = with(subject) {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_CREATE ) {
                    val navigator = AppNavigator(subject, appContainerId)
                    navigatorHolder.setNavigator(navigator)
                }
                else if ( event == Lifecycle.Event.ON_DESTROY ) {
                    navigatorHolder.removeNavigator()
                }
            }
        })
    }
}
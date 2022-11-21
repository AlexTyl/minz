package by.kmmq.minz

import android.app.Application
import by.kmmq.minz.modules.app
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MinzApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MinzApp)
            modules(app)
        }
    }
}
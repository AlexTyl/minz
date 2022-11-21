package by.kmmq.minz.single_activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kmmq.minz.R
import by.kmmq.minz.navigation.INavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator by inject<INavigator<MainActivity>> { parametersOf(R.id.nav_host_container) }
    private val viewModel by viewModel<BaseMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.attach(this)
        viewModel
    }
}
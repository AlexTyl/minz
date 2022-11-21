package by.kmmq.minz.navigation.routers

import by.kmmq.features.products.presentation.list.DishListFragment
import by.kmmq.minz.single_activity.IMainRouter
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MainRouter(private val router: Router) : IMainRouter {
    override fun navigateInitialScreen() {
        router.replaceScreen(FragmentScreen {
            DishListFragment()
        })
    }
}
package by.kmmq.minz.navigation.routers

import android.os.Bundle
import by.kmmq.features.products.presentation.details.DishDetailsFragment
import by.kmmq.features.products.presentation.list.IDishListRouter
import by.kmmq.features.products.presentation.model.DishUI
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class DishListRouter(val router: Router): IDishListRouter {
    override fun navigateToDetails(dish: DishUI) {
         router.navigateTo(FragmentScreen {
             DishDetailsFragment().apply {
                 arguments = Bundle().apply {
                     putParcelable(DishUI::javaClass.name, dish)
                 }
             }
         })
    }
}
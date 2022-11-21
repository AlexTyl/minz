package by.kmmq.features.products.presentation.list

import by.kmmq.features.products.presentation.model.DishUI

interface IDishListRouter {
    fun navigateToDetails(dish: DishUI)
}
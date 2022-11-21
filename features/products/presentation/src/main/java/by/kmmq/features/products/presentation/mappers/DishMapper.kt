package by.kmmq.features.products.presentation.mappers

import android.content.Context
import by.kmmq.features.products.domain.Dish
import by.kmmq.features.products.presentation.R
import by.kmmq.features.products.presentation.model.DishUI

class DishMapper(private val context: Context) {
    fun domainToUI(dish: Dish): DishUI {
        return DishUI(
            dish.id,
            dish.name,
            dish.description,
            dish.image,
            context.getString(R.string.ui_credits, dish.price)
        )
    }
}
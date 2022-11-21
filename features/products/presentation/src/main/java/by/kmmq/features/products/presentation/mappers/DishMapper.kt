package by.kmmq.features.products.presentation.mappers

import by.kmmq.features.products.domain.Dish
import by.kmmq.features.products.presentation.model.DishUI

class DishMapper {
    fun domainToUI(dish: Dish): DishUI {
        return DishUI(
            dish.id,
            dish.name,
            dish.description,
            dish.image,
            dish.price.toString()
        )
    }
}
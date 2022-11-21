package by.kmmq.features.products.domain

import io.reactivex.rxjava3.core.Single

interface GetDishListUseCase {
    operator fun invoke(): Single<List<Dish>>
}
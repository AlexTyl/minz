package by.kmmq.features.products.presentation.list

import by.kmmq.features.products.presentation.model.DishUI
import by.kmmq.minz.utils.RxViewModel
import io.reactivex.rxjava3.core.Observable

abstract class BaseDishListViewModel : RxViewModel() {

    abstract val dishes: Observable<List<DishUI>>
    abstract val state: Observable<ScreenEventState>
    abstract val deleteButtonState: Observable<Boolean>
    abstract val error: Observable<String>

    abstract fun goToDetails(dish: DishUI)

    abstract fun deleteSelected()

    abstract fun switchDish(dish: DishUI)

    sealed class ScreenEventState {
        object Normal: ScreenEventState()
        object Download : ScreenEventState()
    }

}
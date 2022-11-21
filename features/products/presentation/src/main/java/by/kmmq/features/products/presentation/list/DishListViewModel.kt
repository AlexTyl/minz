package by.kmmq.features.products.presentation.list

import android.content.Context
import by.kmmq.features.products.domain.GetDishListUseCase
import by.kmmq.features.products.presentation.mappers.DishMapper
import by.kmmq.features.products.presentation.model.DishUI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class DishListViewModel(
    private val router: IDishListRouter,
    private val getDishListUseCase: GetDishListUseCase,
    context: Context
) : BaseDishListViewModel() {

    override val dishes: BehaviorSubject<List<DishUI>> = BehaviorSubject.createDefault(emptyList())
    override val state: BehaviorSubject<ScreenEventState> = BehaviorSubject.createDefault(
        ScreenEventState.Normal
    )
    override val deleteButtonState: BehaviorSubject<Boolean> =  BehaviorSubject.createDefault(false)

    override val error: PublishSubject<String> = PublishSubject.create()

    private val dishMapper = DishMapper(context)

    private var selectedCount = 0

    init {
        getDishes()
            .subscribe()
            .bindToLifecycle()
    }

    private fun getDishes(): Single<List<DishUI>> {
        return getDishListUseCase()
            .map {
                it.map { dish -> dishMapper.domainToUI(dish) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.onNext(ScreenEventState.Download)
            }
            .doOnSuccess { dishesUI ->
                dishes.onNext(dishesUI)
                selectedCount = 0
                deleteButtonState.onNext(false)
            }
            .doOnError {
                error.onNext(it.localizedMessage ?: "")
            }
            .doFinally {
                state.onNext(ScreenEventState.Normal)
            }
    }


    override fun goToDetails(dish: DishUI) {
        router.navigateToDetails(dish)
    }

    override fun deleteSelected() {
        dishes.onNext(dishes.value?.filter { it.isSwitches.not() } ?: emptyList())
        selectedCount = 0
        deleteButtonState.onNext(false)
    }

    override fun switchDish(dish: DishUI) {
        if (dish.isSwitches)
            selectedCount++
        else
            selectedCount--

        deleteButtonState.onNext(selectedCount > 0)
    }

    override fun downloadDishes() {
        getDishes()
            .subscribe()
            .bindToLifecycle()
    }
}
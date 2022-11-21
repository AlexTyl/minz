package by.kmmq.features.products.di

import by.kmmq.feature.products.data.GetDishListUseCaseMock
import by.kmmq.features.products.domain.GetDishListUseCase
import by.kmmq.features.products.presentation.list.BaseDishListViewModel
import by.kmmq.features.products.presentation.list.DishListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val useCases = module {
    factory<GetDishListUseCase> {
        GetDishListUseCaseMock()
    }
}


private val viewModels = module {
    viewModel<BaseDishListViewModel> {
        DishListViewModel(
            router = get(),
            getDishListUseCase = get()
        )
    }
}

val productsDiModules = useCases + viewModels
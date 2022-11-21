package by.kmmq.minz.modules

import by.kmmq.features.products.di.productsDiModules
import by.kmmq.minz.modules.core.navigationModule
import by.kmmq.minz.single_activity.BaseMainViewModel
import by.kmmq.minz.single_activity.MainViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal val app: List<Module> = navigationModule + productsDiModules + module {
    factory<BaseMainViewModel> { MainViewModel(mainRouter = get()) }
}

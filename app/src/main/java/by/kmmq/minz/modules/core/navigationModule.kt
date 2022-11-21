package by.kmmq.minz.modules.core

import by.kmmq.features.products.presentation.list.IDishListRouter
import by.kmmq.minz.navigation.ActivityNavigator
import by.kmmq.minz.navigation.INavigator
import by.kmmq.minz.navigation.routers.DishListRouter
import by.kmmq.minz.navigation.routers.MainRouter
import by.kmmq.minz.single_activity.IMainRouter
import by.kmmq.minz.single_activity.MainActivity
import org.koin.dsl.module
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

val navigationModule = module {
    single { Cicerone.create() }
    single {
        val cicerone = get<Cicerone<Router>>()
        cicerone.getNavigatorHolder()
    }
    single {
        val cicerone = get<Cicerone<Router>>()
        cicerone.router
    }

    factory<INavigator<MainActivity>> { (appContainerId: Int) ->
        ActivityNavigator(
            navigatorHolder = get(),
            appContainerId = appContainerId
        )
    }

    single<IMainRouter> {
        MainRouter(
            router = get()
        )
    }

    factory<IDishListRouter> {
        DishListRouter(
            router = get()
        )
    }
}
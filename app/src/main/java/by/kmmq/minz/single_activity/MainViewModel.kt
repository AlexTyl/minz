package by.kmmq.minz.single_activity


class MainViewModel(
    private val mainRouter: IMainRouter
) : BaseMainViewModel() {
    init {
        mainRouter.navigateInitialScreen()
    }
}
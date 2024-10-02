package mk.templateApp.two.ui.dynamic

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import mk.templateApp.AndroidApp
import mk.templateApp.commonDomain.dynamicFeature.FeatureType
import mk.templateApp.presenter.base.InjectionActivity
import mk.templateApp.presenter.theming.AppTheme
import mk.templateApp.two.di.ViewModelProvider
import mk.templateApp.two.ui.dynamic.Route.Home
import mk.templateApp.two.ui.dynamic.Route.Second
import mk.templateApp.two.ui.dynamic.Route.Third
import mk.templateApp.two.ui.home.HomeNavEvent.NavigateToSecond
import mk.templateApp.two.ui.home.HomeScreen
import mk.templateApp.two.ui.home.HomeViewModel
import mk.templateApp.two.ui.second.SecondNavEvent.NavigateToThird
import mk.templateApp.two.ui.second.SecondScreen
import mk.templateApp.two.ui.second.SecondViewModel
import mk.templateApp.two.ui.third.ThirdScreen
import mk.templateApp.two.ui.third.ThirdViewModel

@DynamicActivityScope
class DynamicActivity : InjectionActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as AndroidApp).featureLoader.getFeature(FeatureType.D_TWO)
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold { innerPadding ->
                    val controller = rememberNavController()

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = controller,
                        startDestination = Home
                    ) {
                        composable<Home> { backStackEntry ->
                            val viewModel: HomeViewModel = ViewModelProvider.homeViewModel()
                            viewModel.loadInitialData()
                            HomeScreen(viewModel = viewModel)
                            ObserveAsEvents(viewModel.route) {
                                when (it) {
                                    is NavigateToSecond -> controller.navigate(it.route)
                                }
                            }

                        }
                        composable<Second> { backStackEntry ->
                            val route: Second = backStackEntry.toRoute()
                            val viewModel: SecondViewModel = ViewModelProvider.secondViewModel()
                            viewModel.loadInitialData(route)
                            SecondScreen(viewModel = viewModel)
                            ObserveAsEvents(viewModel.route) {
                                when (it) {
                                    is NavigateToThird -> controller.navigate(it.route)
                                }
                            }
                        }
                        composable<Third> { backStackEntry ->
                            val screen: Third = backStackEntry.toRoute()
                            val viewModel: ThirdViewModel = ViewModelProvider.thirdViewModel()
                            viewModel.loadInitialData(screen)
                            ThirdScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun <T> ObserveAsEvents(
    flow: Flow<T?>,
    onEvent: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect {
                    if (it != null) {
                        onEvent(it)
                    }
                }
            }
        }
    }
}

sealed interface HomeRouter : Router {
    data class NavigateToSecond(val second: Second)
}

sealed interface SecondRouter : Router {
    data class NavigateToThird(val third: Third)
}

sealed interface Router


sealed interface Route {

    @Serializable
    data object Home : Route {
        fun navigateToSecond(second: Second, navController: NavController) = navController.navigate(second)
    }

    @Serializable
    data class Second(val arg: String) : Route {
        fun navigateToThird(third: Third, navController: NavController) = navController.navigate(third)
    }

    @Serializable
    data class Third(val arg: String) : Route
}


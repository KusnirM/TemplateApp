package mk.templateApp.two.ui.dynamic

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mk.templateApp.AndroidApp
import mk.templateApp.commonDomain.dynamicFeature.FeatureType
import mk.templateApp.presenter.base.InjectionActivity
import mk.templateApp.presenter.theming.AppTheme
import mk.templateApp.two.ui.home.home
import mk.templateApp.two.ui.movieList.movieList
import mk.templateApp.two.ui.third.third

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
                        startDestination = Route.Caching
                    ) {
                        home(controller)
                        movieList()
                        third()
                    }
                }
            }
        }
    }
}

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data object Caching : Route

    @Serializable
    data class Third(val arg: String) : Route
}


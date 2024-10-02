package mk.templateApp.presenter.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

open class Navigator(
    private val fragment: Fragment,
) {

    fun navigateBack() {
        navController {
            if (!popBackStack()) {
                fragment.requireActivity().finish()
            }
        }
    }

    private fun navController(block: NavController.() -> Unit) {
        block(fragment.findNavController())
    }

    fun navigate(direction: NavDirections, bundle: Bundle? = null, navOptions: NavOptions? = null) {
        navController {
            if (canNavigateTo(direction)) {
                bundle?.apply {
                    direction.arguments.putAll(this)
                }
                navigate(direction, navOptions)
            }
        }
    }

    fun navigate(destinationId: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
        navController {
            if (canNavigateTo(destinationId)) {
                navigate(destinationId, bundle, navOptions)
            }
        }
    }

    private fun NavController.canNavigateTo(direction: NavDirections): Boolean = canNavigateTo(direction.actionId)

    private fun NavController.canNavigateTo(destinationResId: Int): Boolean {
        val currentDestination: NavDestination = currentBackStackEntry?.destination ?: return false
        val navAction = currentDestination.getAction(destinationResId) ?: return false

        val destinationId: Int = navAction.destinationId
        val currentNode: NavGraph? = currentDestination as? NavGraph ?: currentDestination.parent

        return destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null
    }
}

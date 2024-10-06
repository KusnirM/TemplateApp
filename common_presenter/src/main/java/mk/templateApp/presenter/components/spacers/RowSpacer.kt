package mk.templateApp.presenter.components.spacers

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import mk.templateApp.presenter.theming.dp16
import mk.templateApp.presenter.theming.dp32
import mk.templateApp.presenter.theming.dp4
import mk.templateApp.presenter.theming.dp64

object RowSpacer {
    @Composable
    private fun RowScope.Spacer(height: Dp) {
        Spacer(modifier = Modifier.height(height))
    }

    @Composable
    fun RowScope.Spacer4() {
        Spacer(dp4)
    }

    @Composable
    fun RowScope.Spacer16() {
        Spacer(dp16)
    }

    @Composable
    fun RowScope.Spacer32() {
        Spacer(dp32)
    }

    @Composable
    fun RowScope.Spacer64() {
        Spacer(dp64)
    }


}
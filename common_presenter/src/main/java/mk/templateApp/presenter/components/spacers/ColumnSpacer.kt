package mk.templateApp.presenter.components.spacers

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import mk.templateApp.presenter.theming.dp16
import mk.templateApp.presenter.theming.dp32
import mk.templateApp.presenter.theming.dp64

object ColumnSpacer {
    @Composable
    private fun ColumnScope.Spacer(height: Dp) {
        Spacer(modifier = Modifier.height(height))
    }

    @Composable
    fun ColumnScope.Spacer16() {
        Spacer(dp16)
    }

    @Composable
    fun ColumnScope.Spacer32() {
        Spacer(dp32)
    }

    @Composable
    fun ColumnScope.Spacer64() {
        Spacer(dp64)
    }


}
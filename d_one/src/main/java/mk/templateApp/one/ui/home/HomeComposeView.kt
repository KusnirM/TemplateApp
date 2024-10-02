package mk.templateApp.one.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mk.templateApp.presenter.components.Item
import mk.templateApp.presenter.components.spacers.VhiColumnSpacer.Spacer32
import mk.templateApp.presenter.components.text.TextTitleLarge
import mk.templateApp.presenter.theming.AppTheme
import mk.templateApp.presenter.theming.dp16


@PreviewLightDark
@Composable
internal fun HomeComposeView(
    twoClick: () -> Unit = {},
) {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(dp16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextTitleLarge("Dynamic Feature - One")
                }
                Spacer32()

                Column(Modifier.padding(dp16)) {
                    Item("Feature 2", twoClick)
                }
            }
        }
    }
}

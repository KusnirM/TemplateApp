package mk.templateApp.home.features.dashboard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mk.templateApp.components.AppToolbar
import mk.templateApp.components.buttons.ContainedButton
import mk.templateApp.components.text.bodyLarge.TextBodyLarge
import mk.templateApp.theming.AppTheme
import mk.templateApp.theming.dp16
import mk.templateApp.theming.dp32

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun DashboardComposeView(
    @PreviewParameter(DashboardPreviewParams::class) vmState: DashboardViewState,
    backIconOnClick: () -> Unit = {},
    featureAClick: () -> Unit = {},
) {
    AppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            AppToolbar(
                title = "Dashboard",
                backIconOnClick = backIconOnClick,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(top = dp32, bottom = dp16)
            ) {
                when {
                    vmState.loading -> TextBodyLarge("Loading")
                    vmState.errorCode != null -> TextBodyLarge(vmState.errorCode)

                    else -> {
                        TextBodyLarge(vmState.message ?: "")

                        ContainedButton(
                            onClick = featureAClick,
                            modifier = Modifier.padding(dp16),
                            text = "FeatureA"
                        )
                    }
                }
            }
        }
    }
}

internal class DashboardPreviewParams : PreviewParameterProvider<DashboardViewState> {
    override val values: Sequence<DashboardViewState>
        get() = sequenceOf(
            DashboardViewState(
                message = "Sample Message",
            )
        )
}

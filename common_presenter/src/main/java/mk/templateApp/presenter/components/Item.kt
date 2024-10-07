package mk.templateApp.presenter.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mk.templateApp.presenter.components.text.TextBodyLarge
import mk.templateApp.presenter.theming.dp16

@PreviewLightDark
@Composable
fun Item(
    @PreviewParameter(ItemPreviewParams::class) title: String,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dp16),
        onClick = onClick,
    ) {
        TextBodyLarge(title)
    }
}

private class ItemPreviewParams : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("title")
}

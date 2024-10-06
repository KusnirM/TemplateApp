package mk.templateApp.presenter.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import mk.templateApp.presenter.components.text.TextBodyLarge
import mk.templateApp.presenter.theming.dp16

@PreviewLightDark
@Composable
fun Item(
    @PreviewParameter(ItemPreviewParams::class) title: String,
    loading: Boolean = false,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dp16),
        enabled = !loading,
        onClick = onClick,
    ) {
        if (loading) {
            CircularProgressIndicator(Modifier.size(24.dp))
        } else {
            TextBodyLarge(title)
        }
    }
}

private class ItemPreviewParams : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("title")
}

package mk.templateApp.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mk.templateApp.presenter.components.text.TextBodyLarge
import mk.templateApp.presenter.theming.cardElevation
import mk.templateApp.presenter.theming.dp16

@PreviewLightDark
@Composable
fun Item(
    @PreviewParameter(ItemPreviewParams::class) title: String, onClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(cardElevation)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable(enabled = true, onClick = onClick)
                .padding(dp16)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextBodyLarge(title)
        }
    }
}

private class ItemPreviewParams : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("title")
}

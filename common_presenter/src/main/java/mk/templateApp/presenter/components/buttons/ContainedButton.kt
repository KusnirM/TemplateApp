package mk.templateApp.presenter.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.templateApp.presenter.components.text.TextBodyLarge
import mk.templateApp.presenter.theming.dp16


@Composable
fun ContainedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dp16),
        enabled = enabled,
    ) {
        TextBodyLarge(text = text)
    }
}

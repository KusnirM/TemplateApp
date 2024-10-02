package mk.templateApp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import mk.templateApp.components.text.bodyLarge.TextBodyLarge


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String? = null,
    backIcon: ImageVector? = Icons.AutoMirrored.Filled.ArrowBack,
    backIconContentDescription: String = "Back Arrow",
    backIconOnClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            title?.let {
                TextBodyLarge(text = title)
            }
        },
        navigationIcon = {
            backIcon?.let {
                IconButton(onClick = backIconOnClick) {
                    Icon(
                        backIcon,
                        contentDescription = backIconContentDescription,
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
        }
    )
}

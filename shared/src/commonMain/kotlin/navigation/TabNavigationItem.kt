package navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// Tab navigation item UI
@Composable
fun TabNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    title: String
) {

    val selectedColor = if (selected) Color.Blue else Color.Black

    IconButton(
        onClick,
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp)
        ) {

                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(icon, title, tint = selectedColor)
                    Text(title, color = selectedColor)
                }
        }
    }
}
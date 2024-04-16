package scene

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ProfileScene(navigator: Navigator) {

    Column {
        Text("This is a Profile screen")
        Text("Test2")
    }
}
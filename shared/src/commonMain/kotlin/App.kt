import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinContext

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    PreComposeApp {
        KoinContext {
            MaterialTheme {
                Surface {
                    Navigator()
                }
            }
        }
    }
}

expect fun getPlatformName(): String
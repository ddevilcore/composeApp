package navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import scene.ProfileScene
import screens.Camera.CameraScreen
import screens.Home.HomeDetailsScreen
import screens.Home.HomeScreen

// Main navigation node
@Composable
fun Navigator() {
    val snackbarHostState = remember { SnackbarHostState() }
    val navigator = rememberNavigator()
    val routes = RouteList

    val currentEntry = navigator.currentEntry.collectAsState(null)
    val currentRoute = currentEntry.value?.route?.route ?: ""

    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(currentRoute) },
                navigationIcon = {
                    IconButton(onClick = { navigator.goBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
//                actions = {
//                    IconButton(onClick = { onEdit.invoke() }) {
//                        Icon(Icons.Default.Edit, contentDescription = null)
//                    }
//                },
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White,
                cutoutShape = CircleShape,
                contentPadding = PaddingValues(10.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    routes.forEach {
                        TabNavigationItem(
                            selected = currentRoute == it.routeName,
                            onClick = { navigator.navigate(it.routeName) },
                            icon = it.icon,
                            title = it.title,
                        )
                    }
                }
            }
        }
    ) {
        NavHost(
            navigator = navigator,
            initialRoute = Routes.HOME.value,
            modifier = Modifier.padding(it),
            navTransition = NavTransition()
        ) {
            group(route = Routes.HOME.value, initialRoute = Routes.MAIN.value) {
                scene(route = Routes.MAIN.value) {
                    HomeScreen(navigator)
                }
                scene(route = Routes.DETAILS.value) {
                    HomeDetailsScreen(navigator)
                }
                scene(route = Routes.CAMERA.value) {
                    CameraScreen()
                }
            }
            scene(route = Routes.PROFILE.value) {
                ProfileScene(navigator)
            }
        }
    }
}
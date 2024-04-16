package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
// Route and Routes definition
enum class Routes(val value: String, val title: String) {
    HOME(value = "/home", title = "Home"),
    MAIN("/main", "Main"),
    DETAILS("/details", "Home Details"),
    PROFILE("/profile", "Profile"),
    CAMERA(value = "/camera", title = "Camera")
}

data class Route(val routeName: String, val title: String, val icon: ImageVector)

val RouteList: List<Route> = listOfNotNull(
    Route(routeName = Routes.HOME.value, title = Routes.HOME.title, icon = Icons.Default.Home),
//    Route(routeName = Routes.MAIN.value, title = Routes.MAIN.title, icon = Icons.Default.Home),
//    Route(routeName = Routes.DETAILS.value, title = Routes.DETAILS.title, icon = Icons.Default.Info),
    Route(routeName = Routes.PROFILE.value, title = Routes.PROFILE.title, icon = Icons.Default.Person),
)
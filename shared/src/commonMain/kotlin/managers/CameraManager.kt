package managers

import androidx.compose.runtime.Composable
import utils.SharedImage

// By expect keyword we can declare separate platform realization
@Composable
expect fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager


expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
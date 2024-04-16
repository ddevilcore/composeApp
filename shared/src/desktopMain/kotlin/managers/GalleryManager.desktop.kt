package managers

import androidx.compose.runtime.Composable
import utils.SharedImage

actual class GalleryManager actual constructor(onLaunch: () -> Unit) {
    actual fun launch() {
    }
}

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    TODO("Not yet implemented")
}
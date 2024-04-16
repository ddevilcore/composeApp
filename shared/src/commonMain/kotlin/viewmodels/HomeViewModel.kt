package viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.stateholder.SavedStateHolder
import moe.tlaster.precompose.viewmodel.ViewModel
import repositories.HomeRepository

// This class should sync repository data with UI layer

class HomeViewModel(
    private val repository: HomeRepository,
): ViewModel() {
    // Getting phoneNumber from repository class
    val phoneNumber by lazy { repository.getPhoneNumber() }
    // Control flow functions
    fun updatePhoneNumber(number: String) {
        repository.updatePhoneNumber(number)
    }

    fun clearPhoneNumber() {
        repository.clearPhoneNumber()
    }
}
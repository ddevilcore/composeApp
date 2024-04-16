package repositories

import kotlinx.coroutines.flow.MutableStateFlow

// This class should sync local data with backend

class HomeRepository {
    // Definition of basic repository field
    private var phoneNumber = MutableStateFlow("")
    private var imagesStorage = MutableStateFlow(mutableListOf(""))

    // Functions to control repository data
    // Maybe here will be some fun's that works with backend
    fun getPhoneNumber(): MutableStateFlow<String> {
        return phoneNumber
    }

    fun updatePhoneNumber(number: String) {
        phoneNumber.value = number
    }

    fun clearPhoneNumber() {
        phoneNumber.value = ""
    }

    fun addImage(image: String) {
        imagesStorage.value.add(image)
    }

    fun deleteImage(index: Int) {
        imagesStorage.value.removeAt(0)
    }

    fun getImages(): MutableList<String> {
        return imagesStorage.value
    }
}
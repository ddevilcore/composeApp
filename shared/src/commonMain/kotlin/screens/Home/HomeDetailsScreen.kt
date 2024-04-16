package screens.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.Navigator
import viewmodels.HomeViewModel

@Composable
fun HomeDetailsScreen(navigator: Navigator) {
    // Injecting ViewModel
    val viewModel = koinViewModel(HomeViewModel::class)
    // Access to phoneNumber property in repository through ViewModel
    val number by viewModel.phoneNumber.collectAsState()
    // UI declaration
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is a home details screen")
        Text("This is mobile phone typed on previous screen $number")
        Button({ viewModel.clearPhoneNumber() }) {
            Text("Tap to clear phoneNumber")
        }
    }
}
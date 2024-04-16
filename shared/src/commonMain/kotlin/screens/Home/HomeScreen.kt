package screens.Home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.Navigator
import navigation.Routes
import viewmodels.HomeViewModel

@Composable
fun HomeScreen(navigator: Navigator) {
    // Injecting ViewModel
    val viewModel: HomeViewModel = koinViewModel(HomeViewModel::class)
    // Screen state declarations
    var textFieldValue by remember {
        mutableStateOf("")
    }
    val isErrorValue by remember {
        mutableStateOf(textFieldValue.contains("/[a-zA-ZА-Яа-я]/g", true))
    }

    println("isErrorValue $isErrorValue")

    // Local managers and controllers
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // Appearance
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            textStyle = TextStyle(
                fontSize = TextUnit(2F, type = TextUnitType(2L)),
            ),
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = { Text("Номер телефона") },
            isError = isErrorValue,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Phone),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                },
            )
        )

        OutlinedButton(
            onClick = {
                viewModel
                    .updatePhoneNumber(number = textFieldValue)
                navigator.navigate(Routes.DETAILS.value)
            },
        ) {
            Text("Дальше")
        }

        OutlinedButton(
            onClick = {
                navigator.navigate(Routes.CAMERA.value)
            }
        ) {
            Text("Посмотреть камеру")
        }
    }
}

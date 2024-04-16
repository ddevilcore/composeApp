package com.myapplication

import App
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import di.AppModule
import di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher = this.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            println("kilo Permission granted")
        } else {
            print("kilo Permission denied")
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                "CAMERA",
            ) == PackageManager.PERMISSION_GRANTED -> {
                println("kilo//Permission previously granted")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> println("kilo//Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val appModule = AppModule.appModule
        startKoin {
            androidContext(this@MainActivity)
            //            modules(listOf(appModule, repoModule, viewModelModule))
            val modules = listOf(
                appModule
            )
            loadKoinModules(modules)
        }
        setContent { App() }

        requestCameraPermission()
    }
}

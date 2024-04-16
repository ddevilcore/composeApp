package com.myapplication

import App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import di.AppModule
import di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
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
    }
}
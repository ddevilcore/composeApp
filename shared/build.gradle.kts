plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    val precompose_version = "+"
    val camerax_version = "+"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                api(compose.foundation)
                api(compose.animation)

                api("moe.tlaster:precompose:$precompose_version")
                api("moe.tlaster:precompose-viewmodel:$precompose_version") // For ViewModel intergration
                api("moe.tlaster:precompose-koin:$precompose_version") // For Koin intergration
//                implementation("io.insert-koin:koin-androidx-compose:$precompose_version")
                implementation("io.insert-koin:koin-compose:$precompose_version")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                implementation("androidx.camera:camera-camera2:$camerax_version")
                implementation ("androidx.camera:camera-core:${camerax_version}")
                implementation ("androidx.camera:camera-view:${camerax_version}")
                implementation ("androidx.camera:camera-lifecycle:$camerax_version")
                implementation("com.google.accompanist:accompanist-permissions:0.33.0-alpha")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
dependencies {
    implementation("androidx.camera:camera-core:1.3.2")
    implementation("androidx.camera:camera-lifecycle:1.3.2")
    implementation("androidx.databinding:databinding-compiler:8.3.2")
    implementation("androidx.databinding:compiler:3.2.0-alpha11")
}

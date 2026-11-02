plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "bf.mathematiques.generateur"
    compileSdk = 35

    defaultConfig {
        applicationId = "bf.mathematiques.generateur"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

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
        versionCode = 2
        versionName = "1.1"
    }

    signingConfigs {
        create("release") {
            storeFile = file(
                System.getenv("SIGNING_KEYSTORE_PATH")
                    ?: "release-keystore.p12"
            )
            storePassword =
                System.getenv("SIGNING_KEYSTORE_PASSWORD")
            keyAlias =
                System.getenv("SIGNING_KEY_ALIAS")
            keyPassword =
                System.getenv("SIGNING_KEY_PASSWORD")
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig =
                signingConfigs.getByName("release")
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

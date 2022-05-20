plugins {
    id("com.android.library")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api("androidx.security:security-crypto:1.0.0")
    api("androidx.biometric:biometric:1.1.0")
    api("androidx.lifecycle:lifecycle-livedata-core:2.4.1")
}
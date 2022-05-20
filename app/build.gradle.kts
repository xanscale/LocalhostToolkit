plugins {
    id("com.android.library")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 19
        targetSdk = 32
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    api("androidx.activity:activity-ktx:1.4.0")
    api("androidx.exifinterface:exifinterface:1.3.3")
    api("androidx.fragment:fragment-ktx:1.4.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    api("com.google.android.material:material:1.6.0")
}
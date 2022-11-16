plugins {
    kotlin("android")
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace  = "localhost.toolkit"
    compileSdk = 33

    defaultConfig {
        minSdk = 19
        targetSdk = 33
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    api("androidx.activity:activity-ktx:1.6.1")
    api("androidx.exifinterface:exifinterface:1.3.5")
    api("androidx.fragment:fragment-ktx:1.5.4")
    api("androidx.recyclerview:recyclerview:1.2.1")

    api("com.google.android.material:material:1.7.0")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
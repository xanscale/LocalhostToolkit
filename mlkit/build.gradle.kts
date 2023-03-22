plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

android {
    namespace = "it.localhostsoftware.mlkit"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    api("androidx.camera:camera-camera2:1.2.2")
    api("androidx.camera:camera-lifecycle:1.2.2")
    api("androidx.camera:camera-view:1.2.2")
    api("androidx.camera:camera-mlkit-vision:1.2.0-beta02")
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
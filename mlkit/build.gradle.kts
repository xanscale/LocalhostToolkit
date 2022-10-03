plugins {
    kotlin("android")
    id("com.android.library")
    id("maven-publish")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    api("androidx.camera:camera-camera2:1.1.0")
    api("androidx.camera:camera-lifecycle:1.1.0")
    api("androidx.camera:camera-view:1.1.0")

    api("com.google.mlkit:barcode-scanning:17.0.2")
    api("com.google.mlkit:text-recognition:16.0.0-beta6")
    api("com.google.mlkit:image-labeling-custom:17.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.6.4")

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
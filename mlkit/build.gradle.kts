plugins {
    `maven-publish`
    kotlin("android")
    id("com.android.library")
}

android {
    namespace = "it.localhostsoftware.mlkit"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.6.4")

    api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    api("androidx.camera:camera-camera2:1.2.0")
    api("androidx.camera:camera-lifecycle:1.2.0")
    api("androidx.camera:camera-view:1.2.0")
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
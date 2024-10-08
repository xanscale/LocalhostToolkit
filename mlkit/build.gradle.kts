plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "it.localhostsoftware.mlkit"
    compileSdk = 35

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
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    api("androidx.camera:camera-mlkit-vision:1.4.0-rc03")
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
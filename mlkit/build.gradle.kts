import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

android {
    namespace = "it.localhostsoftware.mlkit"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JvmTarget.JVM_11.target
    }

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
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    api("androidx.camera:camera-camera2:1.3.0-beta02")
    api("androidx.camera:camera-lifecycle:1.3.0-beta02")
    api("androidx.camera:camera-view:1.3.0-beta02")
    api("androidx.camera:camera-mlkit-vision:1.3.0-beta02")
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
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "localhost.toolkit"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JvmTarget.JVM_11.target
    }

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdk = 19
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api("androidx.activity:activity:1.7.2")
    api("androidx.exifinterface:exifinterface:1.3.6")
    api("androidx.recyclerview:recyclerview:1.3.1")

    api("com.google.android.material:material:1.9.0")
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
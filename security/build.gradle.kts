plugins {
    kotlin("android")
    id("com.android.library")
    id("maven-publish")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33
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
    api("androidx.lifecycle:lifecycle-livedata-core:2.5.1")
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
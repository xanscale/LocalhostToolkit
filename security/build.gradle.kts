plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "it.localhostsoftware.security"
    compileSdk = 36

    defaultConfig {
        minSdk = 23
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
    api("androidx.lifecycle:lifecycle-livedata-core:2.9.1")
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
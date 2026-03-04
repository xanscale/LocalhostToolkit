plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    alias(androidx.plugins.androidxNavigationSafeargsKotlinGradlePlugin)
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "it.localhostsoftware.core"
    compileSdk = 36

    buildFeatures {
        viewBinding = true
    }

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
    api(androidx.activity.activity)
    api(androidx.exifinterface.exifinterface)
    implementation(androidx.navigation.navigationFragment)
    api(androidx.recyclerview.recyclerview)

    api("com.google.android.material:material:1.13.0")
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
plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    id("androidx.navigation.safeargs.kotlin")
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "it.localhostsoftware.core"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
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
    api("androidx.activity:activity:1.9.3")
    api("androidx.exifinterface:exifinterface:1.3.7")
    implementation("androidx.navigation:navigation-fragment:2.8.5")
    api("androidx.recyclerview:recyclerview:1.3.2")

    api("com.google.android.material:material:1.12.0")
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
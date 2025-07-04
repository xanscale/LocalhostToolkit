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
    compileSdk = 36

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
    api("androidx.activity:activity:1.10.1")
    api("androidx.exifinterface:exifinterface:1.4.1")
    implementation("androidx.navigation:navigation-fragment:2.9.0")
    api("androidx.recyclerview:recyclerview:1.4.0")

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
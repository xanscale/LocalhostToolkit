plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    id("androidx.navigation.safeargs.kotlin")
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "it.localhostsoftware.core"
    compileSdk = 34

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
    api("androidx.activity:activity:1.9.0")
    api("androidx.exifinterface:exifinterface:1.3.7")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
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
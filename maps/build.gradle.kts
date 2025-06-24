plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "it.localhostsoftware.maps"
    compileSdk = 36

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
    implementation("androidx.core:core:1.16.0")
    implementation("androidx.fragment:fragment:1.8.8")
    implementation("com.google.android.gms:play-services-maps:19.2.0")
    implementation("com.huawei.hms:maps:6.11.2.301")
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
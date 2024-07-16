plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "it.localhostsoftware.maps"
    compileSdk = 34

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
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.fragment:fragment:1.8.1")
    implementation("com.google.android.gms:play-services-maps:19.0.0")
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
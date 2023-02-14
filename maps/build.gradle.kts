plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

android {
    namespace = "it.localhostsoftware.maps"
    compileSdk = 33

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
    implementation("androidx.fragment:fragment:1.5.5")
    api("com.google.android.gms:play-services-maps:18.1.0")
    api("com.huawei.hms:maps:6.9.0.300")
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
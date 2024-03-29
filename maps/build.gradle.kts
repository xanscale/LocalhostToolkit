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
    implementation("androidx.fragment:fragment:1.6.2")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
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
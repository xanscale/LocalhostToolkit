plugins {
    `maven-publish`
    kotlin("android")
    id("com.android.library")
}

android {
    namespace  = "it.localhostsoftware.maps"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    api("com.huawei.hms:maps:6.7.1.302")
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
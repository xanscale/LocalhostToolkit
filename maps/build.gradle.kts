plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

android {
    namespace = "it.localhostsoftware.maps"
    compileSdk = 33

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
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
    implementation("androidx.fragment:fragment:1.5.7")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.huawei.hms:maps:6.10.1.300")
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
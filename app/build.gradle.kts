plugins {
    kotlin("android")
    id("com.android.library")
    `maven-publish`
}

android {
    namespace  = "localhost.toolkit"
    compileSdk = 33

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

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
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    api("androidx.activity:activity-ktx:1.7.1")
    api("androidx.exifinterface:exifinterface:1.3.6")
    api("androidx.fragment:fragment-ktx:1.5.7")
    api("androidx.recyclerview:recyclerview:1.3.0")

    api("com.google.android.material:material:1.8.0")
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
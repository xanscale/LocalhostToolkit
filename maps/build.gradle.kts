plugins {
    id("com.android.library")
    `maven-publish`
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "it.localhostsoftware.maps"
    compileSdk = 37

    defaultConfig {
        minSdk = 23
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(androidx.core.core)
    implementation(androidx.fragment.fragment)
    implementation("com.google.android.gms:play-services-maps:20.0.0")
    implementation("com.huawei.hms:maps:6.15.1.318")
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

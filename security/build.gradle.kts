plugins {
    id("com.android.library")
    `maven-publish`
}

kotlin {
    jvmToolchain(25)
}

android {
    namespace = "it.localhostsoftware.security"
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
    api(androidx.security.securityCrypto)
    api(androidx.biometric.biometric)
    api(androidx.lifecycle.lifecycleLivedataCore)
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

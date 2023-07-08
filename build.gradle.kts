plugins {
    kotlin("android") version "1.9.0" apply false
    id("com.android.library") version "8.0.2" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
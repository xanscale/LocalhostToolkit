plugins {
    kotlin("android") version "1.8.21" apply false
    id("com.android.library") version "8.0.1" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
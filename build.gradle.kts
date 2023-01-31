plugins {
    kotlin("android") version "1.7.21" apply false
    id("com.android.library") version "7.4.0" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
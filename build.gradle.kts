plugins {
    kotlin("android") version "1.8.0" apply false
    id("com.android.library") version "7.4.1" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
plugins {
    kotlin("android") version "1.7.0" apply false
    id("com.android.library") version "7.2.1" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
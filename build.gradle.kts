plugins {
    kotlin("android") version "1.7.20" apply false
    id("com.android.library") version "7.3.1" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
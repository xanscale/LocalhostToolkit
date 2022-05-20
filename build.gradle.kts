plugins {
    kotlin("android") version "1.6.21" apply false
    id("com.android.library") version "7.1.3" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
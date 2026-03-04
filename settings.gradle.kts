rootProject.name = "LocalhostToolkit"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://developer.huawei.com/repo/") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://developer.huawei.com/repo/") }
    }
    versionCatalogs {
        create("androidx") {
            from("androidx.gradle:gradle-version-catalog:2026.02.01")
        }
    }
}

include(":core")
include(":maps")
include(":mlkit")
include(":security")
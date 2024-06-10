pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Android Training App"
include(":app")

//CORE
include(":core:common")
include(":core:database")
include(":core:datastore")
include(":core:domain")
include(":core:model")
include(":core:network")
include(":core:ui")


//FEATURE
include(":feature:home")
include(":feature:login")

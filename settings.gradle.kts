dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()

        maven { url = java.net.URI("https://jitpack.io") }
    }
}

rootProject.name = "FakeDeviceLib"
include(":app")
include(":FakeDeviceLib")

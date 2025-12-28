    pluginManagement {
        repositories {
            google {
                content {
                    includeGroupByRegex("com\\.android.*")
                    includeGroupByRegex("com\\.google.*")
                    includeGroupByRegex("androidx.*")
                }
            }
            mavenCentral()
            gradlePluginPortal()
        }
    }

    // Yahan plugins ko define karna zaroori hai
    plugins {
        id("com.android.application") version "8.13.2" apply false
        id("com.google.gms.google-services") version "4.4.2" apply false
        id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    }

    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
        }
    }

    rootProject.name = "Quizz"
    include(":app")
buildscript {
    repositories {
        google()
        mavenCentral()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Version.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}")
        classpath("org.gradle.kotlin:gradle-kotlin-dsl-plugins:${Version.kotlin_dsl_plugins}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}")
    }
}

apply(plugin = "org.gradle.kotlin.kotlin-dsl")
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}
buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }

    }

    dependencies {

        classpath(libs.navigation.safe.args.gradle.plugin)
    }

}

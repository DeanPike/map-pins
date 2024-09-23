// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/domain-group/design-tokens")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv ("GITHUB_TOKEN")
            }
        }
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.secrets) apply false
}
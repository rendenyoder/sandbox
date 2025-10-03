plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "io.sandbox"
    version = "1.0-SNAPSHOT"
}

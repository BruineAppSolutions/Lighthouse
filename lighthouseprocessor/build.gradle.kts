plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "com.bruine"
version = "0.1.0"

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":lighthouse"))
                implementation(libs.kotlin.poet)
                implementation(libs.kotlin.poet.ksp)
                implementation(libs.ksp.processing.api)
            }
        }
    }

}

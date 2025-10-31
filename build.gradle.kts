plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
    alias(libs.plugins.androidLint) apply false
    id("com.vanniktech.maven.publish") version "0.34.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "lighthouse", version.toString())

    pom {
        name = "Lighthouse"
        description = "Annotation based navigation for Compose Multiplatform"
        inceptionYear = "2025"
        url = "https://github.com/BruineAppSolutions/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "rdebruine"
                name = "Richard de Bruine"
                url = "https://github.com/BruineAppSolutions/"
            }
        }
        scm {
            url = "https://github.com/BruineAppSolutions/"
            connection = "scm:git:git://github.com/BruineAppSolutions/lighthouse.git"
            developerConnection = "scm:git:ssh://git@github.com/BruineAppSolutions/lighthouse.git"
        }
    }
}

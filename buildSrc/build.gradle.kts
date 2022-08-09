plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

group = "com.developersancho.buildSrc"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins {
        register("DependencyUpdatePlugin") {
            id = "dependency.update.plugin"
            implementationClass = "plugins.DependencyUpdatePlugin"
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    implementation("com.android.tools.build:gradle:7.2.2")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.42")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
}
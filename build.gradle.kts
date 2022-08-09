plugins {
    id("com.google.devtools.ksp") version "1.7.10-1.0.6" apply false
}

apply<plugins.DependencyUpdatePlugin>()

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
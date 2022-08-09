import com.developersancho.buildsrc.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

android.libraryVariants.all {
    val variantName = name
    kotlin.sourceSets {
        getByName("main") {
            kotlin.srcDir(File("build/generated/ksp/$variantName/kotlin"))
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":libraries:testutils")))
    implementation(project(mapOf("path" to ":libraries:framework")))

    implementation(SupportLib.CoreKtx)

    testImplementation(TestingLib.Junit)
    androidTestImplementation(AndroidTestingLib.JunitExt)
    androidTestImplementation(AndroidTestingLib.EspressoCore)

    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.CoroutineAndroid)

    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
    debugImplementation(NetworkLib.ChuckerDebug)
    releaseImplementation(NetworkLib.ChuckerRelease)

    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)

    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
}
import com.developersancho.buildsrc.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Configs.CompileSdk

    defaultConfig {
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        testInstrumentationRunner = Configs.TestInstrumentationRunner
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

dependencies {
    api(TestingLib.Junit)
    api(AndroidTestingLib.JunitExt)
    api(AndroidTestingLib.EspressoCore)
    api(TestingLib.Coroutine)
    api(TestingLib.Truth)
    api(TestingLib.Robolectric)
    api(TestingLib.Turbine)
    api(TestingLib.Mockk)
    api(TestingLib.Okhttp)
    api(TestingLib.Hamcrest)
    api(TestingLib.Json)
    implementation(NetworkLib.Moshi)
    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.LoggingInterceptor)
}
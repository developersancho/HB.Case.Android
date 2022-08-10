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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Appcompat)
    implementation(SupportLib.Material)

    testImplementation(TestingLib.Junit)
    androidTestImplementation(AndroidTestingLib.JunitExt)
    androidTestImplementation(AndroidTestingLib.EspressoCore)

    implementation(SupportLib.Recyclerview)

    implementation(SupportLib.ActivityKtx)
    implementation(SupportLib.FragmentKtx)

    implementation(SupportLib.Timber)

    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.CoroutineAndroid)

    implementation(SupportLib.LifecycleViewModel)
    implementation(SupportLib.LifecycleRuntime)
    implementation(SupportLib.Paging)

    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.Moshi)
}
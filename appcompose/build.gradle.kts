import com.developersancho.buildsrc.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Configs.CompileSdk

    defaultConfig {
        applicationId = Configs.Compose.ApplicationId
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        versionCode = Configs.Compose.VersionCode
        versionName = Configs.Compose.VersionName
        testInstrumentationRunner = Configs.TestInstrumentationRunner
        vectorDrawables.useSupportLibrary = true
        buildConfigField("String", "BASE_URL", "\"https://itunes.apple.com/\"")
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
        freeCompilerArgs = listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.ComposeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":libraries:framework")))
    testImplementation(project(mapOf("path" to ":libraries:testutils")))

    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Material)
    implementation(SupportLib.ActivityKtx)
    implementation(SupportLib.FragmentKtx)

    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)

    implementation(SupportLib.Coil)
    implementation(SupportLib.Timber)

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

    implementation(SupportLib.LifecycleViewModel)
    implementation(SupportLib.LifecycleRuntime)
    implementation(SupportLib.Paging)

    // Compose Libs
    androidTestImplementation(ComposeLib.TestingLib.ComposeTestJunit)
    debugImplementation(ComposeLib.Tooling)
    debugImplementation(ComposeLib.Manifest)
    implementation(ComposeLib.Ui)
    implementation(ComposeLib.Material)
    implementation(ComposeLib.Preview)
    implementation(ComposeLib.Runtime)
    implementation(ComposeLib.Foundation)
    implementation(ComposeLib.MaterialIconCore)
    implementation(ComposeLib.MaterialIconExtended)
    implementation(ComposeLib.ConstraintLayout)
    implementation(ComposeLib.Paging)
    implementation(ComposeLib.Coil)
    implementation(ComposeLib.AccompanistLib.Systemuicontroller)
    implementation(ComposeLib.HiltNavigation)
    implementation(ComposeLib.NavigationLib.DestinationCore)
    ksp(ComposeLib.NavigationLib.DestinationKsp)
    implementation(ComposeLib.NavigationLib.DestinationAnimation)

}
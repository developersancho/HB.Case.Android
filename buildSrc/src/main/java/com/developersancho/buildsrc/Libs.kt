package com.developersancho.buildsrc

object ComposeLib {
    const val Ui = "androidx.compose.ui:ui:${Versions.Compose}"
    const val Material = "androidx.compose.material:material:${Versions.Compose}"
    const val Preview = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose}"
    const val Runtime = "androidx.compose.runtime:runtime:${Versions.Compose}"
    const val Foundation = "androidx.compose.foundation:foundation:${Versions.Compose}"
    const val MaterialIconCore = "androidx.compose.material:material-icons-core:${Versions.Compose}"
    const val MaterialIconExtended = "androidx.compose.material:material-icons-extended:${Versions.Compose}"
    const val Tooling = "androidx.compose.ui:ui-tooling:${Versions.Compose}"
    const val Manifest = "androidx.compose.ui:ui-test-manifest:${Versions.Compose}"

    const val HiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"

    const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0"
    const val Lottie = "com.airbnb.android:lottie-compose:5.0.3"
    const val Paging = "androidx.paging:paging-compose:1.0.0-alpha14"
    const val Coil = "io.coil-kt:coil-compose:2.1.0"

    object AccompanistLib {
        const val Swiperefresh = "com.google.accompanist:accompanist-swiperefresh:0.25.1"
        const val Systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:0.25.1"
        const val Insets = "com.google.accompanist:accompanist-insets:0.25.1"
        const val PlaceholderMaterial = "com.google.accompanist:accompanist-placeholder-material:0.25.1"
        const val NavigationMaterial = "com.google.accompanist:accompanist-navigation-material:0.25.1"
        const val Permissions = "com.google.accompanist:accompanist-permissions:0.25.1"
        const val Pager = "com.google.accompanist:accompanist-pager:0.25.1"
        const val Indicators = "com.google.accompanist:accompanist-pager-indicators:0.25.1"
        const val Webview = "com.google.accompanist:accompanist-webview:0.25.1"
    }

    object NavigationLib {
        const val Navigation = "androidx.navigation:navigation-compose:2.5.1"
        const val DestinationCore = "io.github.raamcosta.compose-destinations:core:1.7.15-beta"
        const val DestinationKsp = "io.github.raamcosta.compose-destinations:ksp:1.7.15-beta"
        const val DestinationAnimation =
            "io.github.raamcosta.compose-destinations:animations-core:1.7.15-beta"
    }

    object TestingLib {
        const val ComposeTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose}"
    }
}

object SupportLib {
    const val CoreKtx = "androidx.core:core-ktx:1.8.0"
    const val Appcompat = "androidx.appcompat:appcompat:1.4.2"
    const val Material = "com.google.android.material:material:1.6.1"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val Recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
    const val SwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

    const val CoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3"
    const val CoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.3"
    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    const val LifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"

    const val ActivityKtx = "androidx.activity:activity-ktx:1.5.1"
    const val FragmentKtx = "androidx.fragment:fragment-ktx:1.5.1"

    const val Coil = "io.coil-kt:coil:2.1.0"

    const val Timber = "com.jakewharton.timber:timber:5.0.1"
    const val Paging = "androidx.paging:paging-runtime-ktx:3.1.1"
}

object NetworkLib {
    const val Moshi = "com.squareup.moshi:moshi-kotlin:1.13.0"
    const val MoshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"
    const val MoshiLazyAdapter = "com.serjltt.moshi:moshi-lazy-adapters:2.2"
    const val Retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val RetrofitMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"
    const val Okhttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.9"
    const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9"
    const val ChuckerDebug = "com.github.chuckerteam.chucker:library:3.5.2"
    const val ChuckerRelease = "com.github.chuckerteam.chucker:library-no-op:3.5.2"
}

object StorageLib {
    const val RoomKtx = "androidx.room:room-ktx:2.4.3"
    const val RoomCompiler = "androidx.room:room-compiler:2.4.3"
    const val DatastorePref = "androidx.datastore:datastore-preferences:1.0.0"
    const val Datastore = "androidx.datastore:datastore:1.0.0"
    const val SecurityPref = "androidx.security:security-crypto-ktx:1.1.0-alpha03"
}

object DaggerHiltLib {
    const val Android = "com.google.dagger:hilt-android:2.42"
    const val Compiler = "com.google.dagger:hilt-android-compiler:2.42"
    const val Compose = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object TestingLib {
    const val Junit = "junit:junit:4.13.2"
    const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.3"
    const val Truth = "com.google.truth:truth:1.1.3"
    const val Robolectric = "org.robolectric:robolectric:4.8.1"
    const val Turbine = "app.cash.turbine:turbine:0.7.0"
    const val Mockk = "io.mockk:mockk:1.12.4"
    const val Okhttp = "com.squareup.okhttp3:mockwebserver:5.0.0-alpha.9"
    const val Hamcrest = "org.hamcrest:hamcrest-library:2.2"
    const val Json = "org.json:json:20210307"
}

object AndroidTestingLib {
    const val JunitExt = "androidx.test.ext:junit:1.1.3"
    const val EspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
}
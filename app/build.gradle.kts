plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.paymob.movies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.paymob.movies"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Android/Kotlin
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.serialization.json)

    // User Interface
    implementation(libs.androidx.core.splashscreen)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
    implementation(libs.androidx.paging.runtime.ktx)

    // Dependence Injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.hilt.android.testing)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // gson converter
    implementation(libs.converter.gson)
    // picasso
    implementation(libs.picasso)

    implementation(libs.intuitSdb)
    implementation(libs.intuitSsb)
    implementation(libs.jakewhartonTimber)

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.navigationFragmentKtx)
    implementation(libs.navigationUiKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso)
    implementation(libs.retrofit)
    // implementation(libs.converterGson)
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)
    implementation(libs.coroutinesAndroid)
    implementation(libs.coroutinesCore)
    implementation(libs.serializationJson)
    implementation(libs.gson)
    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    implementation(libs.glide)
    implementation(libs.roundedImageView)
    implementation(libs.lifecycleLivedataKtx)
    implementation(libs.lifecycleViewmodelKtx)
}
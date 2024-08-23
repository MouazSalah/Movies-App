plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.safeArgs)
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
        dataBinding = true
        buildConfig = true
    }


    flavorDimensions.add("default") // Define the flavor dimension
    productFlavors {
        create("staging") {
            dimension = "default" // Assign the flavor to the dimension
            applicationId = "com.paymob.movies.staging"
            resValue("string", "app_name", "Movies Staging")

            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/movie/\"")
            buildConfigField("String", "BASE_IMAGES_URL", "\"https://image.tmdb.org/t/p/original\"")
            buildConfigField("String", "API_KEY", "\"169edd5ba67dab5a976067e49fad78d0\"")
            buildConfigField("String", "API_ACCESS_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNjllZGQ1YmE2N2RhYjVhOTc2MDY3ZTQ5ZmFkNzhkMCIsIm5iZiI6MTcyNDMwODYyOC4wMTc5ODUsInN1YiI6IjVkZmI4ZjI4ZDFhODkzMDAxMjgyYzNjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3s1nhqNGiwW_izq8fe9tlLBPSqlhGt9UwA4HuElOUmE\"")
        }

        create("production") {
            dimension = "default" // Assign the flavor to the dimension
            applicationId = "com.paymob.movies"
            resValue("string", "app_name", "Movies")

            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/movie/\"")
            buildConfigField("String", "BASE_IMAGES_URL", "\"https://image.tmdb.org/t/p/original\"")
            buildConfigField("String", "API_KEY", "\"169edd5ba67dab5a976067e49fad78d0\"")
            buildConfigField("String", "API_ACCESS_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNjllZGQ1YmE2N2RhYjVhOTc2MDY3ZTQ5ZmFkNzhkMCIsIm5iZiI6MTcyNDMwODYyOC4wMTc5ODUsInN1YiI6IjVkZmI4ZjI4ZDFhODkzMDAxMjgyYzNjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3s1nhqNGiwW_izq8fe9tlLBPSqlhGt9UwA4HuElOUmE\"")
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

    implementation(libs.glide)
    implementation(libs.roundedImageView)
    implementation(libs.lifecycleLivedataKtx)
    implementation(libs.lifecycleViewmodelKtx)

    //Chuck
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    implementation("com.facebook.shimmer:shimmer:0.5.0")

    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)

    implementation("androidx.fragment:fragment-ktx:1.5.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")
    ksp("androidx.room:room-compiler:2.5.2")
}
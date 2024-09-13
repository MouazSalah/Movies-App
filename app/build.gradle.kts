import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.hiltAndroid)
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

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
        buildConfigField("String", "BASE_IMAGES_URL", properties.getProperty("BASE_IMAGES_URL"))
        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))
        buildConfigField("String", "API_ACCESS_TOKEN", properties.getProperty("API_ACCESS_TOKEN"))
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isShrinkResources = false
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
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    flavorDimensions.add("default") // Define the flavor dimension
    productFlavors {
        create("staging") {
            dimension = "default" // Assign the flavor to the dimension
            applicationId = "com.paymob.movies.staging"
            resValue("string", "app_name", "Movies Staging")
   }

        create("production") {
            dimension = "default" // Assign the flavor to the dimension
            applicationId = "com.paymob.movies"
            resValue("string", "app_name", "Movies")
        }
    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
    //    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // compose
    implementation("androidx.compose.ui:ui:1.1.0-beta03")
    implementation("androidx.activity:activity-compose:1.3.0-rc02")
    implementation("androidx.compose.material:material:1.1.0-beta03")
    implementation("androidx.compose.material:material-icons-extended:1.1.0-beta03")
    implementation("androidx.compose.foundation:foundation:1.1.0-beta03")
    implementation("androidx.compose.foundation:foundation-layout:1.1.0-beta03")
    implementation("androidx.compose.animation:animation:1.1.0-beta03")
    implementation("androidx.compose.runtime:runtime:1.1.0-beta03")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-beta03")
    implementation("androidx.navigation:navigation-compose:2.4.0-beta02")
    implementation("androidx.compose.ui:ui-tooling:1.1.0-beta03")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0-beta03")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")

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

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Chuck
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)

    implementation("androidx.fragment:fragment-ktx:1.5.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")
    ksp("androidx.room:room-compiler:2.5.2")
}
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.gbapp6"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
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
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:${Versions.CORE_KTX}")
    implementation("androidx.appcompat:appcompat:${Versions.APPCOMPAT}")
    implementation("com.google.android.material:material:${Versions.MATERIAL}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}")
    implementation("androidx.legacy:legacy-support-v4:${Versions.SUPPORT_V4}")
    implementation("androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}")
    implementation(project(":database"))

    // RxJava
    implementation("io.reactivex.rxjava3:rxjava:${Versions.RX_JAVA}")
    implementation("io.reactivex.rxjava3:rxkotlin:${Versions.RX_JAVA}")
    implementation("io.reactivex.rxjava3:rxandroid:${Versions.RX_JAVA}")

    // Logging-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:adapter-rxjava3:${Versions.RETROFIT}")

    // Dagger
    implementation("com.google.dagger:dagger:${Versions.DAGGER}")
    implementation("com.google.dagger:dagger-android:${Versions.DAGGER}")
    implementation("com.google.dagger:dagger-android-support:${Versions.DAGGER}")
    kapt("com.google.dagger:dagger-compiler:${Versions.DAGGER}")
    kapt("com.google.dagger:dagger-android-processor:${Versions.DAGGER}")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:${Versions.NAVIGATION}")

    // Koin
    implementation("io.insert-koin:koin-core:${Versions.KOIN}")
    implementation("io.insert-koin:koin-android:${Versions.KOIN}")

    //Glide
    implementation("com.github.bumptech.glide:glide:${Versions.GLIDE}")
    kapt("com.github.bumptech.glide:compiler:${Versions.GLIDE}")

    //Room
    implementation("androidx.room:room-runtime:${Versions.ROOM}")
    kapt("androidx.room:room-compiler:${Versions.ROOM}")
    implementation("androidx.room:room-ktx:${Versions.ROOM}")
}

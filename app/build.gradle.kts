plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.jiromo5.donerhome"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jiromo5.donerhome"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Добавленные зависимости
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.okhttpLoggingInterceptor)
    implementation(libs.securityCrypto)
    implementation(libs.robolectric)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.mockitoInline)
    androidTestImplementation(libs.mockitoCore)
    androidTestImplementation(libs.mockitoInline)
    androidTestImplementation(libs.robolectric)
}
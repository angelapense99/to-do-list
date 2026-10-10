

plugins {
    // Android application plugin
    alias(libs.plugins.android.application)

    // Kotlin support for Android
    alias(libs.plugins.kotlin.android)

    // Jetpack Compose compiler plugin
    alias(libs.plugins.kotlin.compose)

    // Kotlin Symbol Processing (used by Room, Hilt)
    alias(libs.plugins.ksp)

    // Hilt dependency injection plugin
    alias(libs.plugins.hilt)
}

android {

    // Fix for duplicate META-INF resources from dependencies
    packaging {
        resources {
            pickFirsts += "META-INF/gradle/incremental.annotation.processors"
        }
    }

    // Application namespace (package name for generated R class)
    namespace = "com.example.to_do_list"

    // Compile SDK version used to build the app
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {

        // Unique application ID
        applicationId = "com.example.to_do_list"

        // Minimum Android version supported
        minSdk = 24

        // Target Android version
        targetSdk = 36

        versionCode = 1
        versionName = "1.0"

        // Test runner for instrumentation tests
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {
            // Disable code shrinking/obfuscation for release build
            isMinifyEnabled = false

            // Proguard rules for release build
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        // Java language compatibility
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {

        // Enable Jetpack Compose
        compose = true
    }

    kotlin {

        // Set Kotlin JVM target version
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}

dependencies {

    // Core Android extensions
    implementation(libs.androidx.core.ktx)

    // Lifecycle components
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Compose UI foundation
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.core.ktx)

    // Compose testing
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Material icons
    implementation(libs.androidx.compose.material.icons.extended)

    // Room database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlinx.coroutines.android)
    ksp(libs.androidx.room.compiler)

    // Unit testing
    testImplementation(libs.junit)

    // Android UI testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt dependency injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    testImplementation("io.mockk:mockk:1.13.17")
}

import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.dagger.hilt)
    id("kotlin-kapt")
}

val secretProperties = Properties().apply {
    load(project.rootProject.file("secret.properties").inputStream())
}

android {
    namespace = "cu.xetid.dtvc.androidtrainingapp.common"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "APP_NAME", "\"${secretProperties.getProperty("APP_NAME")}\"")
        buildConfigField("String", "VERSION_NAME", "\"${secretProperties.getProperty("VERSION_NAME")}\"")
        buildConfigField("String", "CONSUMER_KEY", "\"${secretProperties.getProperty("CONSUMER_KEY")}\"")
        buildConfigField("String", "CONSUMER_SECRET", "\"${secretProperties.getProperty("CONSUMER_SECRET")}\"")
        buildConfigField("String", "URL_WSO2_HOST", "\"${secretProperties.getProperty("URL_WSO2_HOST")}\"")
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //Core
    implementation(libs.core.ktx)
    coreLibraryDesugaring(libs.com.android.tools.desugar)

    //Hilt
    implementation(libs.com.google.dagger.hilt.android)
    kapt(libs.com.google.dagger.hilt.compiler)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
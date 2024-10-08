plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)


    //kotlin序列化
    kotlin("plugin.serialization") version "1.9.23"

    //依赖注入
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)


}

android {
    namespace = "com.example.music"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.music"
        minSdk = 28
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
        compose = true


        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
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

    implementation(libs.androidx.compose.material.iconsExtended)

    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation(libs.androidx.lifecycle.runtimeCompose)

    //kotlin序列化
    //https://kotlinlang.org/docs/serialization.html
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //region 网络框架
    //https://github.com/square/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //网络框架日志框架
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //类型安全网络框架
    //https://github.com/square/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //让Retrofit支持Kotlinx Serialization
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")


    //图片加载框架
    //https://github.com/coil-kt/coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    //region 依赖注入
    //https://developer.android.google.cn/training/dependency-injection/hilt-android?hl=zh-cn
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    //endregion

    compileOnly(libs.ksp.gradlePlugin)

    val androidx_media3_version = "1.2.1"
    implementation("androidx.media3:media3-exoplayer:$androidx_media3_version")
    implementation("androidx.media3:media3-datasource:$androidx_media3_version")
    implementation("androidx.media3:media3-ui:$androidx_media3_version")
    implementation("androidx.media3:media3-session:$androidx_media3_version")
    implementation("androidx.media3:media3-cast:$androidx_media3_version")

    //browser?.getChildren()?.await()
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.7.1")

    //约束布局
    //https://developer.android.com/develop/ui/compose/layouts/constraintlayout?hl=zh-cn
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
}
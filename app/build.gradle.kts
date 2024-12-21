plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.big0soft.nearexpireadmin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.big0soft.nearexpireadmin"
        minSdk = 28
        targetSdk = 34
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
        buildFeatures {
            dataBinding = true
            viewBinding = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }

    }



    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.roundedimageview)
    implementation(libs.adapter.guava)
    implementation(libs.converter.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.rules)
    // mockito web server dependency
    testImplementation(libs.mockwebserver)
    // mockito core
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.espresso.contrib) // For RecyclerView actions
    // rx java
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    // retrofit
    implementation(platform(libs.okhttp.bom))
    // define any required OkHttp artifacts without version
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.adapter.rxjava3)

    implementation(libs.appintro)
    androidTestImplementation(libs.mockito.android)


    // Java language implementation navigation

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    // refresh layout
    implementation(libs.swiperefreshlayout)

    // Testing Navigation
    androidTestImplementation(libs.navigation.testing)


    // Testing Navigation
    androidTestImplementation(libs.navigation.testing)


    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    // RxJava3 support for Room
    implementation(libs.room.rxjava3)

    // rx java
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    // fragment testing core
    debugImplementation(libs.fragment.testing.manifest)
    androidTestImplementation(libs.fragment.testing)

    // For FragmentScenarioRule and Fragment testing
    androidTestImplementation(libs.rules)
    // otp view library
    implementation(libs.otpview)

    //    design size layout
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    implementation(libs.lottie)

//    //    pagination
//    val paging_version = "3.3.2"
//    implementation("androidx.paging:paging-runtime:$paging_version")
//    implementation("androidx.paging:paging-rxjava3:$paging_version")


    implementation(libs.circleimageview)
    //image picker
    implementation(libs.imagepicker)

    androidTestImplementation(libs.truth)
    testImplementation(libs.room.testing)
    testImplementation(libs.truth)
    testImplementation(libs.core.testing)

    androidTestImplementation(libs.room.testing)

    androidTestImplementation(libs.core.testing)

    implementation("com.github.acefalobi:android-stepper:0.3.0")

    // Glide dependency
    implementation(libs.glide)
    implementation(project(path = ":resource"))


}


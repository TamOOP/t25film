plugins {
    id("com.android.application")
}


android {
    namespace = "com.huce.t25film"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.huce.t25film"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }


}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    implementation("androidx.room:room-common:2.6.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.android.volley:volley:1.2.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    //retrofit
    val retrofitVersion = "2.9.0"
    val compilerVersion = "3.2.0-alpha10"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //gson
    val gsonVersion = "2.10.1"
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.github.MikeOrtiz:TouchImageView:1.4.1")
    //lifecycle
}
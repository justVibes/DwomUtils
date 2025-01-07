plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
    id ("kotlin-kapt")
    id ("io.realm.kotlin")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
//    id("com.vanniktech.maven.publish") version "0.29.0"
}

android {
    namespace = "com.example.ui_components"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material3:material3-window-size-class-android:1.3.1")

    //Coil
    implementation(libs.coil.compose)

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.49")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(libs.androidx.room.common)
    kapt ("com.google.dagger:hilt-compiler:2.45")

    //Firebase-firestore
    implementation(libs.firebase.storage.ktx)
    implementation("com.google.firebase:firebase-firestore:25.1.0")

    //Json serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    //Apache POI
    implementation("org.apache.poi:poi-ooxml:5.2.3")

    // Mongo Realm
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("io.realm.kotlin:library-base:1.6.1")
}

/*mavenPublishing {
    coordinates(
        groupId = "io.github.bizmapsolution",
        artifactId = "book-it-components",
        version = "0.0.1"
    )

    pom{
        name.set("Book It Components")
        description.set("Library used to host both the ui components and the models used in the Book It App")
        inceptionYear.set("2025")
        url.set("")

        licenses {
            license {

            }
        }
    }
}*/

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

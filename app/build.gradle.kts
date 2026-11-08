plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.toggrobot"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.toggrobot"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "2.0"
    }

    // Imza anahtari repoda DEGIL: GitHub Secrets'tan gelir (SIGNING_KEYSTORE_B64, SIGNING_PASSWORD).
    // Ayni anahtarla imzalandigi icin yeni surumler guncelleme olarak kurulur, ayarlar silinmez.
    signingConfigs {
        create("release") {
            val ks = System.getenv("SIGNING_KEYSTORE_FILE")
            val pw = System.getenv("SIGNING_PASSWORD")
            if (ks != null && pw != null) {
                storeFile = file(ks)
                storePassword = pw
                keyAlias = "toggrobot"
                keyPassword = pw
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.json:json:20240303")
}

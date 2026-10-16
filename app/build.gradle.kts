import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties().apply {
    load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.typheye.wgpro"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.typheye.wgpro"
        minSdk = 24
        targetSdk = 36
        versionCode = 2610160
        versionName = "3.26.10.Alpha7"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file(keystoreProperties["release.store.file"] as String)
            storePassword = keystoreProperties["release.store.password"] as String
            keyAlias = keystoreProperties["release.key.alias"] as String
            keyPassword = keystoreProperties["release.key.password"] as String

            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
            enableV4Signing = true
        }

        create("release") {
            storeFile = file(keystoreProperties["release.store.file"] as String)
            storePassword = keystoreProperties["release.store.password"] as String
            keyAlias = keystoreProperties["release.key.alias"] as String
            keyPassword = keystoreProperties["release.key.password"] as String

            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
            enableV4Signing = true
        }
    }

    packaging {
        // 引擎二进制要能从 nativeLibraryDir 被 exec，必须解压落地（对应 extractNativeLibs=true）
        jniLibs {
            useLegacyPackaging = true
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.core:core:1.17.0")
    implementation("com.journeyapps:zxing-android-embedded:3.5.0")
    implementation("com.google.zxing:core:3.5.3")
    implementation("androidx.appcompat:appcompat:1.7.1")
    // 预见式返回（predictive back）：Fragment 需 1.6+、Activity 需 1.6+，这里显式抬版本
    implementation("androidx.activity:activity:1.11.0")
    implementation("androidx.fragment:fragment:1.8.9")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    // Vela 引擎取帧/注入走 gRPC（原始字节 marshaller，不需要 protoc 生成代码）
    implementation("io.grpc:grpc-okhttp:1.68.1")
    implementation("io.grpc:grpc-stub:1.68.1")
    implementation("io.grpc:grpc-api:1.68.1")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    implementation("androidx.preference:preference:1.2.1")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}

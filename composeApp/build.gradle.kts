import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}

kotlin {

//    compilerOptions {
//        languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
//        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
//    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.runtime)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.transitions)

            // Use api, not implementation!
            api(libs.appyx.utils.material3)
            api(libs.appyx.spotlight)
            api(libs.appyx.navigation)
            implementation(libs.appyx.interactions)
            implementation(libs.appyx.backstack)

            // peekaboo-ui
            implementation(libs.peekaboo.ui)
            // peekaboo-image-picker
            implementation(libs.peekaboo.image.picker)

            implementation(libs.uuid)
            implementation(libs.eva.icons)

            // https://github.com/alexzhirkevich/compottie
            implementation(libs.lottie.kmp)

            // Viewmodel support for Compose multiplatform
            implementation(libs.lifecycle.viewmodel.compose)

            implementation(libs.voyager.koin)

            implementation(libs.kamel)

            // https://github.com/DevSrSouza/compose-icons
            implementation(libs.composeIcons.evaIcons)
            implementation(libs.composeIcons.cssGg)
            implementation(libs.composeIcons.feather)
            implementation(libs.composeIcons.simpleIcons)
            implementation(libs.composeIcons.weatherIcons)
            implementation(libs.composeIcons.fontAwesome)
            implementation(libs.composeIcons.lineAwesome)
            implementation(libs.composeIcons.linea)
            implementation(libs.composeIcons.octicons)
            implementation(libs.composeIcons.tablerIcons)

            /**
             * 高斯模糊
             * https://github.com/chrisbanes/haze
             */
            implementation(libs.haze.compose)
            implementation(libs.haze.materials)

            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)

            /**
             *  file picker and save
             */
            implementation(libs.filekit.core)
            implementation(libs.filekit.compose)

        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.android.driver)
            implementation(libs.androidx.compose.material3)
            implementation(libs.koin.androidx.compose)


            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.voyager.hilt)

            implementation("androidx.activity:activity-compose:1.7.2")

        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.native.driver)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {

        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }

}

sqldelight {
//    databases {
//        create("AppDatabase") {
//            packageName.set("com.jetbrains.spacetutorial.cache")
//        }
//    }
    databases {
        create("AppDatabase") {
            packageName.set("org.example.project.cache")
        }
    }
}

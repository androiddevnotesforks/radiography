import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  defaultConfig {
    minSdk = 21
    targetSdk = 34
    applicationId = "com.squareup.radiography.sample.compose"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.sample.compiler.get()
  }

  packaging {
    resources.excludes += listOf(
      "META-INF/AL2.0",
      "META-INF/LGPL2.1"
    )
  }
    namespace = "com.squareup.radiography.sample.compose"
    testNamespace = "com.squareup.radiography.sample.compose.test"
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs += listOf(
      "-Xopt-in=kotlin.RequiresOptIn"
    )
  }
}

dependencies {
  implementation(project(":radiography"))
  implementation(libs.appCompat)
  implementation(libs.compose.sample.activity)
  implementation(libs.compose.sample.material)
  implementation(libs.compose.sample.tooling)

  androidTestImplementation(libs.compose.sample.testing)
  androidTestImplementation(libs.test.androidx.rules)
  androidTestImplementation(libs.test.androidx.runner)
}

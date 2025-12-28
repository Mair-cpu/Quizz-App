// Project-level build.gradle.kts
plugins {
    // Version hatane se "already on classpath" wala error khatam ho jayega
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("com.google.gms.google-services") apply false
}
# Quizz-App

1st day work 

Splash Screeen
This XML layout defines a splash screen for an Android app using ConstraintLayout as the root container, which fills the entire screen with a dark blue-gray background (#1E1E2C) and is linked to SplashScreenActivity. It includes an ImageView for the app logo, sized at 120dp and centered horizontally at the top, referencing a drawable resource with accessibility description. Below it are three TextViews: the first displays the bold white app name "Quiz App" in 26sp font, centered; the second shows the light gray tagline "Test Your Knowledge" in 14sp, also centered; and the third, a small gray footer text "Powered by Android Studio" in 12sp, positioned at the bottom with a 20dp margin, all elements constrained to maintain responsive centering and vertical stacking for a clean, branded display.


<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#1E1E2C"
    tools:context=".SplashScreenActivity">

    <!-- App Logo -->
    <ImageView
        android:id="@+id/imgLogo"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/ic_logo"
        android:contentDescription="App Logo"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/txtAppName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- App Name -->
    <TextView
        android:id="@+id/txtAppName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Quiz App"
        android:textSize="26sp"
        android:textStyle="bold"
        android:textColor="#FFFFFF"
        app:layout_constraintTop_toBottomOf="@id/imgLogo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Tagline -->
    <TextView
        android:id="@+id/txtTagline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Test Your Knowledge"
        android:textSize="14sp"
        android:textColor="#B0BEC5"
        app:layout_constraintTop_toBottomOf="@id/txtAppName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Footer Text -->
    <TextView
        android:id="@+id/txtFooter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Powered by Android Studio"
        android:textSize="12sp"
        android:textColor="#90A4AE"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginBottom="20dp"/>

</androidx.constraintlayout.widget.ConstraintLayout>




package com.example.adminquizzz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LinearLayout splashContainer = findViewById(R.id.splashContainer);
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);

        // Animation start karein
        if (splashContainer != null) {
            splashContainer.startAnimation(zoomIn);
        }

        // 2.5 seconds baad jab animation peak par ho, tab switch karein
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);

            // Ye transition effect front screen laane mein madad karega
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            finish();
        }, 1300);
    }
}
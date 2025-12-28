package com.example.quizz;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// SAHI IMPORT: Purana adminquizzz wala delete kar ke ye add karein
import com.example.quizz.R;

public class QuizDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_dashboard);

        String data = getIntent().getStringExtra("DATA");

        if (data != null && data.contains("|")) {
            String[] p = data.split("\\|");

            TextView title = findViewById(R.id.dashTitle);
            TextView date = findViewById(R.id.dashDate);
            TextView correct = findViewById(R.id.dashCorrect);
            TextView wrong = findViewById(R.id.dashWrong);
            TextView percentage = findViewById(R.id.dashPercent);

            if (p.length >= 5) {
                title.setText(p[0].trim());
                date.setText("Date: " + p[1].trim());
                correct.setText(p[2].trim());
                wrong.setText(p[4].trim());

                try {
                    float c = Float.parseFloat(p[2].trim());
                    float t = Float.parseFloat(p[3].trim());
                    if (t > 0) {
                        int perc = (int) ((c / t) * 100);
                        percentage.setText(perc + "%");
                    } else {
                        percentage.setText("0%");
                    }
                } catch (Exception e) {
                    percentage.setText("0%");
                }
            }

            // Animation apply karne se pehle null check zaroori hai
            View dashCard = findViewById(R.id.mainDashCard);
            if (dashCard != null) {
                dashCard.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            }
        }
    }

    public void goBack(View v) {
        finish();
    }
}
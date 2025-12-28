package com.example.quizz;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

// SAHI IMPORT: Purana adminquizzz wala aur android.os.Build wala delete kar dein
import com.example.quizz.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PlayQuizActivity extends AppCompatActivity {

    TextView tvQuestionText, tvQuestionCount, tvScore, tvQuizTitle;
    Button btn1, btn2, btn3, btn4, btnNext;
    String[] questionsArray;
    String quizName = "Unnamed Quiz";
    int currentIdx = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        tvQuizTitle = findViewById(R.id.tvQuizTitle);
        tvQuestionText = findViewById(R.id.tvQuestionText);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        tvScore = findViewById(R.id.tvScore);
        btn1 = findViewById(R.id.btnOpt1);
        btn2 = findViewById(R.id.btnOpt2);
        btn3 = findViewById(R.id.btnOpt3);
        btn4 = findViewById(R.id.btnOpt4);
        btnNext = findViewById(R.id.btnNext);

        String rawData = getIntent().getStringExtra("QUIZ_DATA");

        if (rawData != null && rawData.contains("!")) {
            try {
                String[] splitData = rawData.split("!");
                quizName = splitData[0];
                tvQuizTitle.setText(quizName);
                questionsArray = splitData[1].split("#");
                loadQuestion();
            } catch (Exception e) {
                Toast.makeText(this, "Invalid Quiz Format", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "No Quiz Data Found", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnNext.setOnClickListener(v -> {
            currentIdx++;
            loadQuestion();
            btnNext.setVisibility(View.GONE);
        });
    }

    private void loadQuestion() {
        if (currentIdx < questionsArray.length) {
            resetButtons();
            String[] parts = questionsArray[currentIdx].split("\\|");

            if (parts.length < 6) {
                currentIdx++;
                loadQuestion();
                return;
            }

            tvQuestionCount.setText("Question: " + (currentIdx + 1) + "/" + questionsArray.length);
            tvQuestionText.setText(parts[0]);
            btn1.setText(parts[1]);
            btn2.setText(parts[2]);
            btn3.setText(parts[3]);
            btn4.setText(parts[4]);

            Animation slideIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            // Ensure karein ke layout mein 'questionCard' ID maujood hai
            View card = findViewById(R.id.questionCard);
            if (card != null) card.startAnimation(slideIn);

            final String correctAns = parts[5].trim();
            View.OnClickListener listener = v -> checkAnswer((Button) v, correctAns);
            btn1.setOnClickListener(listener);
            btn2.setOnClickListener(listener);
            btn3.setOnClickListener(listener);
            btn4.setOnClickListener(listener);
        } else {
            showFinalResult();
        }
    }

    private void checkAnswer(Button clicked, String correct) {
        disableButtons();
        if (clicked.getText().toString().trim().equalsIgnoreCase(correct)) {
            score++;
            tvScore.setText("Score: " + score);
            clicked.setBackgroundColor(Color.parseColor("#4CAF50"));
            clicked.setTextColor(Color.WHITE);
        } else {
            clicked.setBackgroundColor(Color.parseColor("#F44336"));
            clicked.setTextColor(Color.WHITE);
            showRightAnswer(correct);
        }
        btnNext.setVisibility(View.VISIBLE);
        btnNext.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
    }

    private void showRightAnswer(String correct) {
        if (btn1.getText().toString().trim().equalsIgnoreCase(correct)) btn1.setBackgroundColor(Color.parseColor("#4CAF50"));
        else if (btn2.getText().toString().trim().equalsIgnoreCase(correct)) btn2.setBackgroundColor(Color.parseColor("#4CAF50"));
        else if (btn3.getText().toString().trim().equalsIgnoreCase(correct)) btn3.setBackgroundColor(Color.parseColor("#4CAF50"));
        else if (btn4.getText().toString().trim().equalsIgnoreCase(correct)) btn4.setBackgroundColor(Color.parseColor("#4CAF50"));
    }

    private void resetButtons() {
        btn1.setEnabled(true); btn2.setEnabled(true);
        btn3.setEnabled(true); btn4.setEnabled(true);

        btn1.setBackgroundColor(Color.WHITE);
        btn2.setBackgroundColor(Color.WHITE);
        btn3.setBackgroundColor(Color.WHITE);
        btn4.setBackgroundColor(Color.WHITE);

        btn1.setTextColor(Color.parseColor("#000080"));
        btn2.setTextColor(Color.parseColor("#000080"));
        btn3.setTextColor(Color.parseColor("#000080"));
        btn4.setTextColor(Color.parseColor("#000080"));
    }

    private void disableButtons() {
        btn1.setEnabled(false); btn2.setEnabled(false);
        btn3.setEnabled(false); btn4.setEnabled(false);
    }

    private void showFinalResult() {
        int total = questionsArray.length;
        int wrong = total - score;
        saveToHistory(quizName, score, total, wrong);

        new AlertDialog.Builder(this)
                .setTitle("Results: " + quizName)
                .setMessage("Correct: " + score + "\nWrong: " + wrong)
                .setPositiveButton("Main Menu", (d, w) -> finish())
                .setCancelable(false).show();
    }

    private void saveToHistory(String title, int correct, int total, int wrong) {
        SharedPreferences sp = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        String oldHistory = sp.getString("history", "");
        String date = new SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(new Date());

        // Format matching with Adapter: Title | Date | Correct | Total | Wrong #
        String newEntry = title + " | " + date + " | " + correct + " | " + total + " | " + wrong + "#";
        sp.edit().putString("history", newEntry + oldHistory).apply();
    }
}
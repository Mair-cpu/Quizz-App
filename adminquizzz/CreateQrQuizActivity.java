package com.example.adminquizzz;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.adminquizzz.databinding.ActivityCreateQrQuizBinding;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.ArrayList;

public class CreateQrQuizActivity extends AppCompatActivity {

    ActivityCreateQrQuizBinding binding;
    // Sawalon ko jama karne ke liye list
    ArrayList<String> quizList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateQrQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1. Sawal ko List mein add karne ka button
        binding.btnAddQuestion.setOnClickListener(v -> {
            String q = binding.etQuestion.getText().toString().trim();
            String a = binding.etOp1.getText().toString().trim();
            String b = binding.etOp2.getText().toString().trim();
            String c = binding.etOp3.getText().toString().trim();
            String d = binding.etOp4.getText().toString().trim();
            String ans = binding.etCorrectAns.getText().toString().trim();

            if (q.isEmpty() || ans.isEmpty() || a.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Ek sawal ka data format: Q|A|B|C|D|Ans
                String questionData = q + "|" + a + "|" + b + "|" + c + "|" + d + "|" + ans;
                quizList.add(questionData);

                // UI update
                binding.tvCount.setText("Questions Added: " + quizList.size());
                clearInputs();
                Toast.makeText(this, "Question added to list!", Toast.LENGTH_SHORT).show();
            }
        });

        // 2. Final QR Generate karne ka button (Title logic added here)
        binding.btnGenerateQR.setOnClickListener(v -> {
            // Quiz ka naam (Title) lena
            String quizTitle = binding.etQuizTitle.getText().toString().trim();

            if (quizTitle.isEmpty()) {
                Toast.makeText(this, "Please enter Quiz Title first!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (quizList.isEmpty()) {
                Toast.makeText(this, "Add at least one question first", Toast.LENGTH_SHORT).show();
                return;
            }

            // Naya Format: Title ! Question1 # Question2 ...
            StringBuilder masterString = new StringBuilder();
            masterString.append(quizTitle).append("!"); // Pehle Title phir '!'

            for (int i = 0; i < quizList.size(); i++) {
                masterString.append(quizList.get(i));
                // Agar aakhri sawal nahi hai to '#' lagao
                if (i < quizList.size() - 1) {
                    masterString.append("#");
                }
            }
            generateQR(masterString.toString());
        });
    }

    private void clearInputs() {
        binding.etQuestion.setText("");
        binding.etOp1.setText("");
        binding.etOp2.setText("");
        binding.etOp3.setText("");
        binding.etOp4.setText("");
        binding.etCorrectAns.setText("");
        binding.etQuestion.requestFocus();
    }

    private void generateQR(String data) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            // 800x800 size for better quality
            Bitmap bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 800, 800);
            binding.ivQRCode.setImageBitmap(bitmap);
            binding.qrCard.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Master QR with Title Generated!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Data too large for QR!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
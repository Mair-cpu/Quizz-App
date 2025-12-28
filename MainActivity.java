package com.example.quizz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu, btnOpenChat;
    FloatingActionButton btnScanQuiz;
    RecyclerView rvHistory, rvOnline;

    List<QuizModel> onlineQuizList = new ArrayList<>();
    QuizAdapter onlineAdapter;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() != null) {
                    Intent intent = new Intent(MainActivity.this, PlayQuizActivity.class);
                    intent.putExtra("QUIZ_DATA", result.getContents());
                    startActivity(intent);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navigationView);
        menu = findViewById(R.id.menu);
        btnScanQuiz = findViewById(R.id.btnScanQuiz);
        rvHistory = findViewById(R.id.rvCategory);
        rvOnline = findViewById(R.id.rvOnline);
        btnOpenChat = findViewById(R.id.btnChat);

        rvOnline.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        onlineAdapter = new QuizAdapter(onlineQuizList, this);
        rvOnline.setAdapter(onlineAdapter);

        rvHistory.setLayoutManager(new LinearLayoutManager(this));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.share) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Try this Quiz App: https://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        menu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        if (btnOpenChat != null) {
            btnOpenChat.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UserChatActivity.class)));
        }

        btnScanQuiz.setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Scan Quiz QR Code");
            barcodeLauncher.launch(options);
        });

        loadHistory();
        fetchOnlineQuizzes();
    }

    private void fetchOnlineQuizzes() {
        String url = "http://quiz-app-2025.infinityfreeapp.com/api.php?action=get_all";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    onlineQuizList.clear();
                    try {
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                onlineQuizList.add(new QuizModel(obj.getString("id"), obj.getString("title"), obj.getString("quiz_data")));
                            }
                        } else {
                            addDefaultCloudQuizzes(); // Agar server empty ho
                        }
                        onlineAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        addDefaultCloudQuizzes(); // Agar parsing error ho
                    }
                }, error -> {
            addDefaultCloudQuizzes(); // Agar internet ya server ka masla ho
        });
        Volley.newRequestQueue(this).add(request);
    }

    private void addDefaultCloudQuizzes() {
        onlineQuizList.clear();

        // 1. Programming Quiz
        String pData = "Python Basics!Who developed Python?|Guido van Rossum|Elon Musk|Bill Gates|Mark Zuckerberg|Guido van Rossum#Which keyword is used for function?|def|func|function|define|def";
        onlineQuizList.add(new QuizModel("1", "Python Basics", pData));

        // 2. Islamic Quiz
        String iData = "Islamic Quiz!How many Surahs in Quran?|114|110|120|115|114#First Prophet of Allah?|Adam (AS)|Nuh (AS)|Musa (AS)|Isa (AS)|Adam (AS)";
        onlineQuizList.add(new QuizModel("2", "Islamic Knowledge", iData));

        // 3. Math Quiz
        String mData = "Mental Math!Square root of 64?|8|6|7|9|8#What is 15 x 3?|45|30|50|40|45";
        onlineQuizList.add(new QuizModel("3", "Mental Math", mData));

        onlineAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHistory();
    }

    private void loadHistory() {
        SharedPreferences sp = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        String data = sp.getString("history", "");
        if (!data.isEmpty()) {
            String[] items = data.split("#");
            List<String> historyList = new ArrayList<>();
            for (String item : items) {
                if (!item.trim().isEmpty()) historyList.add(item);
            }
            rvHistory.setAdapter(new HistoryAdapter(historyList, this));
        }
    }
}
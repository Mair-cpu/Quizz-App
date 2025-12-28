package com.example.quizz;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.quizz.databinding.ActivityUserChatBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserChatActivity extends AppCompatActivity {

    private ActivityUserChatBinding binding;
    private ChatAdapter adapter;
    private List<ChatMessage> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ChatAdapter(messageList);
        binding.chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.chatRecyclerView.setAdapter(adapter);

        binding.btnSendMessage.setOnClickListener(v -> {
            String msg = binding.etUserMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                sendMessage(msg);
            }
        });

        if (messageList.isEmpty()) {
            addMessage(new ChatMessage("Hello! I am your Quiz Assistant. Ask me anything about the quizzes!", false));
        }
    }

    private void sendMessage(String userMsg) {
        addMessage(new ChatMessage(userMsg, true));
        binding.etUserMessage.setText("");

        // Bot Response Logic with Delay
        binding.chatRecyclerView.postDelayed(() -> {
            String botReply = getBotResponse(userMsg); // Yahan method call ho raha hai
            addMessage(new ChatMessage(botReply, false));
        }, 1000);
    }

    // --- YE HAI WO METHOD JO AAPNE POOCHA ---
    private String getBotResponse(String input) {
        String msg = input.toLowerCase().trim();

        // 1. Greetings
        if (msg.contains("hi") || msg.contains("hello") || msg.contains("hey"))
            return "Hello! Are you ready to solve some English quizzes today?";

        // 2. Quiz Related
        if (msg.contains("start") || msg.contains("play"))
            return "Just go to the dashboard and click on a category to start your journey!";
        if (msg.contains("score") || msg.contains("point"))
            return "You get 1 point for every correct answer. Check your history for details.";
        if (msg.contains("wrong") || msg.contains("mistake"))
            return "Don't worry about mistakes! They are the best way to learn new things.";
        if (msg.contains("hard") || msg.contains("difficult"))
            return "Practice makes perfect. Try starting with easier quizzes first.";

        // 3. Subject Based
        if (msg.contains("math")) return "Math quizzes help you improve your calculation speed!";
        if (msg.contains("science")) return "Science quizzes are great for learning about the world.";
        if (msg.contains("history")) return "History quizzes tell us amazing stories about the past.";

        // 4. Random English Default Replies (Agar kuch match na kare)
        String[] randomReplies = {
                "That's a great question! Keep exploring the app to learn more.",
                "I'm here to help you become a quiz master. What else do you want to know?",
                "Interesting! Make sure to complete at least one quiz today.",
                "I understand. You should try the latest quiz on the dashboard!",
                "Knowledge is power! Ask me more about quizzes or scores.",
                "I'm still learning, but I can tell you that daily practice is key to success."
        };

        return randomReplies[new Random().nextInt(randomReplies.length)];
    }

    private void addMessage(ChatMessage message) {
        messageList.add(message);
        adapter.notifyItemInserted(messageList.size() - 1);
        binding.chatRecyclerView.scrollToPosition(messageList.size() - 1);
    }
}
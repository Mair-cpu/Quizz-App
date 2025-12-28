package com.example.quizz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatMessage {
    private String text;
    private boolean isUser;
    private long timestamp; // Message ka waqt save karne ke liye

    // Constructor
    public ChatMessage(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
        this.timestamp = System.currentTimeMillis(); // Mojooda waqt khud ba khud set ho jayega
    }

    // Getters
    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Waqt ko "10:30 AM" jaisay format mein dikhanay ke liye helper method
    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    // Setters (Agar baad mein zaroorat paray)
    public void setText(String text) {
        this.text = text;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
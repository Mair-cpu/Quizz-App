package com.example.quizz;

public class QuizModel {
    private String id;
    private String title;
    private String quizData;

    public QuizModel(String id, String title, String quizData) {
        this.id = id;
        this.title = title;
        this.quizData = quizData;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getQuizData() { return quizData; }

    // FIX: Is method ko ya to delete kar dein ya sahi return type dein
    public String getQuizId() {
        return id;
    }
}
package com.example.quizz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private List<QuizModel> quizList;
    private Context context;

    public QuizAdapter(List<QuizModel> quizList, Context context) {
        this.quizList = quizList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Ensure karein ke layout file ka naam 'item_online_quiz.xml' hi hai
        View view = LayoutInflater.from(context).inflate(R.layout.item_online_quiz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizModel quiz = quizList.get(position);
        holder.title.setText(quiz.getTitle());

        holder.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayQuizActivity.class);

            // Sahi tariqa data bhejane ka
            intent.putExtra("QUIZ_ID", quiz.getId()); // QuizModel mein getId() method hai
            intent.putExtra("QUIZ_DATA", quiz.getQuizData());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Button btnStart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // In IDs ko apni XML file (item_online_quiz.xml) se zaroor match karein
            title = itemView.findViewById(R.id.tvOnlineQuizTitle);
            btnStart = itemView.findViewById(R.id.btnStartOnline);
        }
    }
}
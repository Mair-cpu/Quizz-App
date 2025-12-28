package com.example.quizz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<String> historyList;
    private Context context;

    public HistoryAdapter(List<String> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Aapka layout file name 'history_item.xml' hona chahiye
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String rawData = historyList.get(position);

        // Data format: Title | Date | Correct | Total | Wrong
        if (rawData.contains("|")) {
            String[] parts = rawData.split("\\|");
            if (parts.length >= 5) {
                holder.tvTitle.setText(parts[0].trim());       // Quiz Title
                holder.tvDate.setText(parts[1].trim());        // Date
                holder.tvCorrect.setText(parts[2].trim());     // Correct Score
                holder.tvTotal.setText(parts[3].trim());       // Total Questions
                holder.tvWrong.setText("Wrong: " + parts[4].trim()); // Wrong count
            }
        }

        // Click Logic: Dashboard par bhejne ke liye
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuizDashboardActivity.class);
            intent.putExtra("DATA", rawData);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Aapke naye XML ke mutabiq variables
        TextView tvTitle, tvDate, tvCorrect, tvTotal, tvWrong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // XML ki IDs se match kar liya gaya hai
            tvTitle = itemView.findViewById(R.id.tvHistoryTitle);
            tvDate = itemView.findViewById(R.id.tvHistoryDate);
            tvCorrect = itemView.findViewById(R.id.tvHistoryCorrect);
            tvTotal = itemView.findViewById(R.id.tvHistoryTotal);
            tvWrong = itemView.findViewById(R.id.tvHistoryWrong);
        }
    }
}
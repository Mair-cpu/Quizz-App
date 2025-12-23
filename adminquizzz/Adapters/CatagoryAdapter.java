package com.example.adminquizzz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminquizzz.Models.CatagoryModel;
import com.example.adminquizzz.R;
import com.example.adminquizzz.databinding.RvCatagoryDesignBinding;

import java.util.ArrayList;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.ViewHolder> {

    Context context;
    ArrayList<CatagoryModel> list;

    public CatagoryAdapter(Context context, ArrayList<CatagoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_catagory_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatagoryModel model = list.get(position);

        holder.binding.categoryName.setText(model.getCategoryName());

        Glide.with(context)
                .load(model.getCategoryImage())
                .placeholder(R.drawable.logo2)
                .into(holder.binding.categoryImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RvCatagoryDesignBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvCatagoryDesignBinding.bind(itemView);
        }
    }
}
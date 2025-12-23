package com.example.adminquizzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu; // Naya Import
import android.view.MenuItem; // Naya Import
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.adminquizzz.Adapters.CatagoryAdapter;
import com.example.adminquizzz.Models.CatagoryModel;
import com.example.adminquizzz.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    CatagoryAdapter adapter;
    ArrayList<CatagoryModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // --- UPDATED: Toolbar ko setup karna taake menu nazar aaye ---
        setSupportActionBar(binding.toolbar);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        binding.shimmerDialog.startShimmer();
        list = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rvCategory.setLayoutManager(layoutManager);

        adapter = new CatagoryAdapter(this, list);
        binding.rvCategory.setAdapter(adapter);

        database.getReference().child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CatagoryModel model = dataSnapshot.getValue(CatagoryModel.class);
                        if (model != null) {
                            model.setKey(dataSnapshot.getKey());
                            list.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    binding.shimmerDialog.stopShimmer();
                    binding.shimmerDialog.setVisibility(View.GONE);
                } else {
                    binding.shimmerDialog.stopShimmer();
                    binding.shimmerDialog.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Category not Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.shimmerDialog.stopShimmer();
                binding.shimmerDialog.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.uploadCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    // --- NEW: Menu create karne ka method ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // --- NEW: Menu par click handle karne ka method ---
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_qr_quiz) {
            Intent intent = new Intent(MainActivity.this, CreateQrQuizActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
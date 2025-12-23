package com.example.adminquizzz;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminquizzz.Models.CatagoryModel;
import com.example.adminquizzz.databinding.ActivityUploadCategoryBinding;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;

public class UploadCategoryActivity extends AppCompatActivity {

    ActivityUploadCategoryBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Dialog loadingDialog;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.setCancelable(false);

        binding.fetchImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });

        binding.btnUploadCat.setOnClickListener(v -> {
            String categoryName = binding.edtCategory.getText().toString();
            if (categoryName.isEmpty()) {
                binding.edtCategory.setError("Enter Name");
            } else if (imageUri == null) {
                Toast.makeText(this, "Select Image", Toast.LENGTH_SHORT).show();
            } else {
                uploadToFirebase(categoryName);
            }
        });
    }

    private void uploadToFirebase(String name) {
        loadingDialog.show();
        // Create reference in Firebase Storage
        StorageReference reference = storage.getReference().child("categories").child(new Date().getTime() + "");

        reference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
            reference.getDownloadUrl().addOnSuccessListener(uri -> {
                CatagoryModel model = new CatagoryModel(name, uri.toString());
                database.getReference().child("categories").push().setValue(model)
                        .addOnSuccessListener(unused -> {
                            loadingDialog.dismiss();
                            finish();
                        });
            });
        }).addOnFailureListener(e -> {
            loadingDialog.dismiss();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null) {
            imageUri = data.getData();
            binding.categoryImage.setImageURI(imageUri);
        }
    }
}
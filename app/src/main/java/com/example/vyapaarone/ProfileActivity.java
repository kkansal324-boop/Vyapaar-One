package com.example.vyapaarone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import android.content.ContentValues;
import android.os.Environment;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtBusinessName;
    private TextView txtOwner;
    private TextView txtMobile;
    private TextView txtEmail;
    private TextView txtAddress;
    private TextView txtGST;
    CircleImageView imgProfile;
    private Uri imageUri;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private Button btnEditProfile;

    private DatabaseHelper databaseHelper;
    private BusinessProfile profile;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgProfile = findViewById(R.id.imgProfile);

        txtBusinessName = findViewById(R.id.txtBusinessName);
        txtOwner = findViewById(R.id.txtOwner);
        txtMobile = findViewById(R.id.txtMobile);
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        txtGST = findViewById(R.id.txtGST);

        btnEditProfile = findViewById(R.id.btnEditProfile);

        databaseHelper = new DatabaseHelper(this);
        profile = databaseHelper.getBusinessProfile();

        loadProfile();

        btnEditProfile.setOnClickListener(v -> {

            Intent intent =
                    new Intent(ProfileActivity.this,
                            BusinessProfileActivity.class);

            startActivity(intent);

        });
        imgProfile.setOnClickListener(v -> showImageOptions());
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK &&
                            result.getData() != null) {

                        imageUri = result.getData().getData();

                        imgProfile.setImageURI(imageUri);

                        if (profile != null) {

                            profile.setProfileImage(imageUri.toString());

                            databaseHelper.updateBusinessProfile(profile);
                        }
                    }

                });

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK &&
                            result.getData() != null) {

                        Bitmap bitmap =
                                (Bitmap) result.getData()
                                        .getExtras()
                                        .get("data");

                        imgProfile.setImageBitmap(bitmap);

                    }

                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadProfile();
    }
    private void loadProfile() {

        BusinessProfile profile = databaseHelper.getBusinessProfile();

        if (profile == null) {
            return;
        }

        txtBusinessName.setText(profile.getBusinessName());
        txtOwner.setText(profile.getOwnerName());
        txtMobile.setText("📞 " + profile.getPhone());
        txtEmail.setText("📧 " + profile.getEmail());
        txtAddress.setText("📍 " + profile.getAddress());
        txtGST.setText("🧾 " + profile.getGstNumber());
        if (profile.getProfileImage() != null &&
                !profile.getProfileImage().isEmpty()) {

            imgProfile.setImageURI(
                    Uri.parse(profile.getProfileImage()));
        }
    }
    private void showImageOptions() {

        String[] options = {
                "Take Photo",
                "Choose from Gallery"
        };

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setTitle("Profile Picture");

        builder.setItems(options, (dialog, which) -> {

            if (which == 0) {

                openCamera();

            } else {

                openGallery();

            }

        });

        builder.show();
    }
    private void openGallery() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{
                                Manifest.permission.READ_MEDIA_IMAGES},
                        200);

                return;
            }

        } else {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        200);

                return;
            }

        }

        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        galleryLauncher.launch(intent);

    }
    private void openCamera() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{
                            Manifest.permission.CAMERA},
                    201);

            return;
        }

        Intent intent =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        cameraLauncher.launch(intent);

    }
}
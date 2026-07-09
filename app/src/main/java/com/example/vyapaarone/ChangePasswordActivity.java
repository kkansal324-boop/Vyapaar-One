package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputEditText currentPassword;
    TextInputEditText newPassword;
    TextInputEditText confirmPassword;

    Button updateButton;

    DatabaseHelper databaseHelper;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        currentPassword = findViewById(R.id.etCurrentPassword);
        newPassword = findViewById(R.id.etNewPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);

        updateButton = findViewById(R.id.btnUpdatePassword);

        updateButton.setOnClickListener(v -> {

            String current = currentPassword.getText().toString().trim();
            String newPass = newPassword.getText().toString().trim();
            String confirm = confirmPassword.getText().toString().trim();

            if (current.isEmpty()
                    || newPass.isEmpty()
                    || confirm.isEmpty()) {

                currentPassword.setError("Required");
                return;
            }

            if (!newPass.equals(confirm)) {

                confirmPassword.setError("Passwords do not match");
                return;
            }

            String username = sessionManager.getUsername();

            boolean success =
                    databaseHelper.updatePassword(
                            username,
                            current,
                            newPass);

            if (success) {

                android.widget.Toast.makeText(
                        this,
                        "Password Updated Successfully",
                        android.widget.Toast.LENGTH_SHORT).show();

                finish();

            } else {

                android.widget.Toast.makeText(
                        this,
                        "Current Password Incorrect",
                        android.widget.Toast.LENGTH_SHORT).show();
            }

        });

    }
}
package com.example.vyapaarone;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etUserId, etShopName, etMobile, etGSTIN, etPassword;
    Button registerBtn, backBtn;
    Switch showPasswordSwitch;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper = new DatabaseHelper(this);
        etUsername = findViewById(R.id.etUsername);
        etUserId = findViewById(R.id.etUserId);
        etShopName = findViewById(R.id.etShopName);
        etMobile = findViewById(R.id.etMobile);
        etGSTIN = findViewById(R.id.etGSTIN);
        etPassword = findViewById(R.id.etPassword);

        registerBtn = findViewById(R.id.registerBtn);
        backBtn = findViewById(R.id.backBtn);

        showPasswordSwitch = findViewById(R.id.showPasswordSwitch);

        showPasswordSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etPassword.setTransformationMethod(
                        HideReturnsTransformationMethod.getInstance());
            } else {
                etPassword.setTransformationMethod(
                        PasswordTransformationMethod.getInstance());
            }
            etPassword.setSelection(etPassword.getText().length());
        });

        registerBtn.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String loginId = etUserId.getText().toString().trim();
            String shop = etShopName.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String gst = etGSTIN.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty()
                    || loginId.isEmpty()
                    || shop.isEmpty()
                    || mobile.isEmpty()
                    || password.isEmpty()) {

                Toast.makeText(this,
                        "Fill all required fields",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            if (databaseHelper.isUsernameExists(username)) {

                Toast.makeText(this,
                        "Username already exists",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            boolean success =
                    databaseHelper.registerUser(
                            username,
                            loginId,
                            shop,
                            mobile,
                            gst,
                            password);

            if (success) {

                Toast.makeText(this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT).show();

                finish();

            } else {

                Toast.makeText(this,
                        "Registration Failed",
                        Toast.LENGTH_SHORT).show();
            }

        });

        backBtn.setOnClickListener(v -> finish());
    }
}
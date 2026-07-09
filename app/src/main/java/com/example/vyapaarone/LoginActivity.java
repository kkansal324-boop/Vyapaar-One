package com.example.vyapaarone;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    private SessionManager sessionManager;
    Button loginBtn, registerBtn;
    Switch showPasswordSwitch;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        if (sessionManager.isLoggedIn()) {

            startActivity(new Intent(
                    LoginActivity.this,
                    DashboardActivity.class));

            finish();

        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        showPasswordSwitch = findViewById(R.id.showPasswordSwitch);

        showPasswordSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                password.setTransformationMethod(
                        HideReturnsTransformationMethod.getInstance());
            } else {
                password.setTransformationMethod(
                        PasswordTransformationMethod.getInstance());
            }

            password.setSelection(password.getText().length());
        });

        loginBtn.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {

                Toast.makeText(this,
                        "Enter Username and Password",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            int userId = databaseHelper.loginUser(user, pass);

            if (userId != -1) {

                sessionManager.createLoginSession(userId, user);

                Toast.makeText(this,
                        "Login Successful",
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(
                        LoginActivity.this,
                        DashboardActivity.class));

                finish();

            } else {

                Toast.makeText(this,
                        "Invalid Username or Password",
                        Toast.LENGTH_SHORT).show();
            }

        });
        registerBtn.setOnClickListener(v -> {

            Intent intent =
                    new Intent(LoginActivity.this,
                            RegisterActivity.class);

            startActivity(intent);

        });
    }
}
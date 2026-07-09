package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreditActivity extends AppCompatActivity {

    private EditText edtAmount;
    private EditText edtReason;
    private Button btnSave;

    private DatabaseHelper db;

    private int customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        edtAmount = findViewById(R.id.edtAmount);
        edtReason = findViewById(R.id.edtReason);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        customerId = getIntent().getIntExtra("customer_id", -1);

        btnSave.setOnClickListener(v -> saveCredit());
    }

    private void saveCredit() {

        String amountText = edtAmount.getText().toString().trim();

        if (amountText.isEmpty()) {

            Toast.makeText(this,
                    "Enter Amount",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        double amount = Double.parseDouble(amountText);

        CustomerLedgerEntry entry =
                new CustomerLedgerEntry();

        entry.setCustomerId(customerId);

        entry.setType("CREDIT");

        entry.setTitle(
                edtReason.getText().toString().trim().isEmpty()
                        ? "Manual Credit"
                        : edtReason.getText().toString().trim()
        );

        entry.setAmount(amount);

        entry.setDate(
                new SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                ).format(new Date())
        );

        if (db.insertLedgerEntry(entry)) {

            Toast.makeText(
                    this,
                    "Credit Added Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Failed to Save Credit",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
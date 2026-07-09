package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DebitActivity extends AppCompatActivity {

    private EditText edtAmount;
    private EditText edtReason;
    private Button btnSave;

    private DatabaseHelper db;

    private int customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        edtAmount = findViewById(R.id.edtAmount);
        edtReason = findViewById(R.id.edtReason);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        customerId = getIntent().getIntExtra("customer_id", -1);

        btnSave.setOnClickListener(v -> saveDebit());
    }

    private void saveDebit() {

        String amountText = edtAmount.getText().toString().trim();
        String reason = edtReason.getText().toString().trim();

        if (amountText.isEmpty()) {
            edtAmount.setError("Enter Amount");
            edtAmount.requestFocus();
            return;
        }

        double amount = Double.parseDouble(amountText);

        if (amount <= 0) {
            edtAmount.setError("Amount must be greater than zero");
            edtAmount.requestFocus();
            return;
        }

        CustomerLedgerEntry entry = new CustomerLedgerEntry();

        entry.setCustomerId(customerId);
        entry.setType("DEBIT");
        entry.setAmount(amount);

        if (reason.isEmpty()) {
            entry.setTitle("Manual Debit");
        } else {
            entry.setTitle(reason);
        }

        entry.setDate(
                new SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                ).format(new Date())
        );

        boolean success = db.insertLedgerEntry(entry);

        if (success) {

            Toast.makeText(
                    this,
                    "Debit Added Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            setResult(RESULT_OK);

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Unable to Save Debit",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
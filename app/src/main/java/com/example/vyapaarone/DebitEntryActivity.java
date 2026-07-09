package com.example.vyapaarone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DebitEntryActivity extends AppCompatActivity {

    EditText edtAmount, edtReason;
    Button btnSave;

    DatabaseHelper db;

    int customerId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_entry);

        edtAmount = findViewById(R.id.edtAmount);
        edtReason = findViewById(R.id.edtReason);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        customerId = getIntent().getIntExtra("customer_id",-1);

        btnSave.setOnClickListener(v -> saveDebit());
    }

    private void saveDebit(){

        if(edtAmount.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Enter Amount",Toast.LENGTH_SHORT).show();
            return;
        }

        double amount =
                Double.parseDouble(
                        edtAmount.getText().toString());

        String reason =
                edtReason.getText().toString();

        CustomerLedgerEntry entry =
                new CustomerLedgerEntry();

        entry.setCustomerId(customerId);
        entry.setType("DEBIT");
        entry.setTitle(reason);
        entry.setAmount(amount);

        entry.setDate(
                new SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault())
                        .format(new Date())
        );

        if(db.insertLedgerEntry(entry)){
            Toast.makeText(this,"Debit Added",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show();
        }

    }
}
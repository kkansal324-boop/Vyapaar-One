package com.example.vyapaarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerLedgerDetailsActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private Button btnAddPayment;
    private Button btnDebit;
    private Button btnCredit;

    private TextView txtCustomerName;
    private TextView txtCustomerPhone;
    private TextView txtTotalSales;
    private TextView txtTotalPaid;
    private TextView txtRemaining;
    private TextView txtLedgerBalance;
    private RecyclerView recyclerTransactions;

    private DatabaseHelper db;

    private CustomerTransactionAdapter adapter;
    private ArrayList<CustomerTransaction> transactionList;

    private int customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_ledger_details);

        btnBack = findViewById(R.id.btnBack);

        btnAddPayment = findViewById(R.id.btnAddPayment);
        btnDebit = findViewById(R.id.btnDebit);
        btnCredit = findViewById(R.id.btnCredit);

        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtCustomerPhone = findViewById(R.id.txtCustomerPhone);
        txtTotalSales = findViewById(R.id.txtTotalSales);
        txtTotalPaid = findViewById(R.id.txtTotalPaid);
        txtRemaining = findViewById(R.id.txtRemaining);
        txtLedgerBalance = findViewById(R.id.txtLedgerBalance);
        recyclerTransactions = findViewById(R.id.recyclerTransactions);
        recyclerTransactions.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);

        customerId = getIntent().getIntExtra("customer_id", -1);

        loadData();

        btnBack.setOnClickListener(v -> finish());

        // These features will be implemented next
        btnAddPayment.setOnClickListener(v -> {

            Intent intent = new Intent(
                    CustomerLedgerDetailsActivity.this,
                    SelectPendingOrderActivity.class);

            intent.putExtra("customer_id", customerId);

            startActivity(intent);

        });

        btnDebit.setOnClickListener(v -> {

            Intent intent = new Intent(
                    CustomerLedgerDetailsActivity.this,
                    DebitActivity.class);

            intent.putExtra("customer_id", customerId);

            startActivity(intent);

        });

        btnCredit.setOnClickListener(v -> {

            Intent intent = new Intent(
                    CustomerLedgerDetailsActivity.this,
                    CreditActivity.class);

            intent.putExtra("customer_id", customerId);

            startActivity(intent);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {

        CustomerLedger ledger = db.getCustomerLedgerById(customerId);

        if (ledger != null) {

            txtCustomerName.setText(ledger.getCustomerName());

            txtCustomerPhone.setText(ledger.getCustomerPhone());

            txtTotalSales.setText("₹ " + ledger.getTotalSales());

            txtTotalPaid.setText("₹ " + ledger.getTotalPaid());

            txtRemaining.setText("₹ " + ledger.getPendingAmount());

            double ledgerBalance = db.getLedgerBalance(customerId);

            txtLedgerBalance.setText("₹ " + ledgerBalance);

        }

        transactionList = db.getCustomerTransactions(customerId);

        adapter = new CustomerTransactionAdapter(transactionList);

        recyclerTransactions.setAdapter(adapter);
    }
}
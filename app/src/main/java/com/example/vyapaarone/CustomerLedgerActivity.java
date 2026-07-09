package com.example.vyapaarone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerLedgerActivity extends AppCompatActivity {

    RecyclerView recyclerCustomerLedger;

    DatabaseHelper databaseHelper;

    ArrayList<CustomerLedger> ledgerList;

    CustomerLedgerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_ledger);

        recyclerCustomerLedger = findViewById(R.id.recyclerCustomerLedger);

        databaseHelper = new DatabaseHelper(this);

        ledgerList = databaseHelper.getCustomerLedger();

        adapter = new CustomerLedgerAdapter(ledgerList);

        recyclerCustomerLedger.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerCustomerLedger.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ledgerList.clear();
        ledgerList.addAll(databaseHelper.getCustomerLedger());

        adapter.notifyDataSetChanged();
    }
}
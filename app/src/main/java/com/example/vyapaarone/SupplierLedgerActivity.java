package com.example.vyapaarone;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupplierLedgerActivity extends AppCompatActivity {

    private RecyclerView recyclerSupplierLedger;
    private TextView txtEmpty;

    private DatabaseHelper databaseHelper;
    private ArrayList<SupplierLedger> ledgerList;
    private SupplierLedgerAdapter adapter;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_ledger);

        recyclerSupplierLedger = findViewById(R.id.recyclerSupplierLedger);
        txtEmpty = findViewById(R.id.txtEmpty);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        recyclerSupplierLedger.setLayoutManager(
                new LinearLayoutManager(this));

        loadLedger();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLedger();
    }

    private void loadLedger() {

        ledgerList =
                databaseHelper.getSupplierLedger(
                        sessionManager.getUserId());

        if (ledgerList == null)
            ledgerList = new ArrayList<>();

        adapter = new SupplierLedgerAdapter(this, ledgerList);

        recyclerSupplierLedger.setAdapter(adapter);

        if (ledgerList.isEmpty()) {

            txtEmpty.setVisibility(View.VISIBLE);
            recyclerSupplierLedger.setVisibility(View.GONE);

        } else {

            txtEmpty.setVisibility(View.GONE);
            recyclerSupplierLedger.setVisibility(View.VISIBLE);

        }
    }
}
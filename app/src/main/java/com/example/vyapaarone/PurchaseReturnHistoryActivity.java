package com.example.vyapaarone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchaseReturnHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    ArrayList<PurchaseReturn> returnList;

    PurchaseReturnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_return_history);

        recyclerView=findViewById(R.id.recyclerPurchaseReturn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper=new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        returnList = databaseHelper.getAllPurchaseReturns(
                sessionManager.getUserId());
        adapter=new PurchaseReturnAdapter(this,returnList);

        recyclerView.setAdapter(adapter);

    }
}
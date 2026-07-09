package com.example.vyapaarone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StockMovementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private DatabaseHelper db;

    private ArrayList<StockMovement> list;

    private StockMovementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_movement);

        recyclerView = findViewById(R.id.recyclerStockMovement);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);

        list = db.getAllStockMovements();

        adapter = new StockMovementAdapter(list);

        recyclerView.setAdapter(adapter);
    }
}
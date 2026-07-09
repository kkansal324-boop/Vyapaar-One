package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class InventorySummaryActivity extends AppCompatActivity {

    private TextView txtTotalProducts;
    private TextView txtInventoryValue;
    private TextView txtLowStock;
    private TextView txtOverStock;
    private RecyclerView recyclerInventory;
    private SessionManager sessionManager;
    private InventoryValuationAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_summary);

        txtTotalProducts = findViewById(R.id.txtTotalProducts);
        txtInventoryValue = findViewById(R.id.txtInventoryValue);
        txtLowStock = findViewById(R.id.txtLowStock);
        txtOverStock = findViewById(R.id.txtOverStock);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        recyclerInventory = findViewById(R.id.recyclerInventory);

        recyclerInventory.setLayoutManager(
                new LinearLayoutManager(this));

        ArrayList<InventoryValuation> list =
                databaseHelper.getInventoryValuation(sessionManager.getUserId());

        adapter = new InventoryValuationAdapter(this, list);

        recyclerInventory.setAdapter(adapter);

        loadSummary();
    }

    private void loadSummary() {

        int totalProducts =
                databaseHelper.getTotalProducts(sessionManager.getUserId());
        double inventoryValue =
                databaseHelper.getTotalInventoryValue(sessionManager.getUserId());
        int lowStock =
                databaseHelper.getLowStockCount(sessionManager.getUserId());
        int overStock =
                databaseHelper.getOverstockCount(sessionManager.getUserId());

        txtTotalProducts.setText(
                String.valueOf(totalProducts));

        txtInventoryValue.setText(
                "₹" + String.format("%.2f", inventoryValue));

        txtLowStock.setText(
                String.valueOf(lowStock));

        txtOverStock.setText(
                String.valueOf(overStock));

        ArrayList<InventoryValuation> list =
                databaseHelper.getInventoryValuation(sessionManager.getUserId());

        adapter = new InventoryValuationAdapter(
                this,
                list);

        recyclerInventory.setAdapter(adapter);
    }
}
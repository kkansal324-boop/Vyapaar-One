package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ProfitLossActivity extends AppCompatActivity {

    private TextView txtTotalPurchase;
    private TextView txtTotalSales;
    private TextView txtInventoryValue;
    private TextView txtGrossProfit;
    private TextView txtTotalProducts;
    private TextView txtTotalSuppliers;
    private TextView txtBusinessStatus;
    private TextView txtNetProfit;
    private SessionManager sessionManager;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_loss);

        txtTotalPurchase = findViewById(R.id.txtTotalPurchase);
        txtTotalSales = findViewById(R.id.txtTotalSales);
        txtInventoryValue = findViewById(R.id.txtInventoryValue);
        txtGrossProfit = findViewById(R.id.txtGrossProfit);
        txtTotalProducts = findViewById(R.id.txtTotalProducts);
        txtTotalSuppliers = findViewById(R.id.txtTotalSuppliers);
        txtNetProfit = findViewById(R.id.txtNetProfit);
        txtBusinessStatus = findViewById(R.id.txtBusinessStatus);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {

        double totalPurchase =
                databaseHelper.getTotalPurchaseAmount(sessionManager.getUserId());
        double totalSales =
                databaseHelper.getTotalSalesAmount(sessionManager.getUserId());
        double inventoryValue =
                databaseHelper.getTotalInventoryValue(sessionManager.getUserId());
        double grossProfit = totalSales - totalPurchase;

        double expenses = 0;

        double netProfit = grossProfit - expenses;

        int totalProducts =
                databaseHelper.getTotalProducts(sessionManager.getUserId());
        int totalSuppliers =
                databaseHelper.getTotalSuppliers(sessionManager.getUserId());
        txtTotalPurchase.setText(
                "₹ " + String.format(Locale.getDefault(), "%.2f", totalPurchase));

        txtTotalSales.setText(
                "₹ " + String.format(Locale.getDefault(), "%.2f", totalSales));

        txtInventoryValue.setText(
                "₹ " + String.format(Locale.getDefault(), "%.2f", inventoryValue));

        txtGrossProfit.setText(
                "₹ " + String.format(Locale.getDefault(), "%.2f", grossProfit));

        txtNetProfit.setText(
                "₹ " + String.format(Locale.getDefault(), "%.2f", netProfit));

        // ADD THIS HERE
        if (netProfit >= 0) {

            txtBusinessStatus.setText("✔ PROFIT");

            txtBusinessStatus.setTextColor(
                    getResources().getColor(android.R.color.holo_green_dark));

        } else {

            txtBusinessStatus.setText("✖ LOSS");

            txtBusinessStatus.setTextColor(
                    getResources().getColor(android.R.color.holo_red_dark));
        }

        txtTotalProducts.setText(String.valueOf(totalProducts));

        txtTotalSuppliers.setText(String.valueOf(totalSuppliers));
    }
}
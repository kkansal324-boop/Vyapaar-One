package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class ReportsActivity extends AppCompatActivity {

    private TextView txtTodaySales;
    private TextView txtMonthSales;
    private TextView txtRevenue;
    private TextView txtOrders;
    private TextView txtTopProducts;
    private TextView txtLowStock;

    // Inventory Report
    private TextView txtTotalProducts;
    private TextView txtInventoryValue;
    private TextView txtLowStockCount;
    private TextView txtOutOfStock;
    private TextView txtPendingAmount;
    private TextView txtCustomerDue;
    private Spinner spinnerReportFilter;

    private LinearLayout layoutTodaySales;
    private LinearLayout layoutMonthSales;
    private LinearLayout layoutRevenue;
    private LinearLayout layoutOrders;
    private LinearLayout layoutCustomer;
    private LinearLayout layoutInventory;
    private LinearLayout layoutTopProducts;
    private LinearLayout layoutLowStock;
    private SessionManager sessionManager;
    private Button btnExportPdf;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        sessionManager = new SessionManager(this);
        db = new DatabaseHelper(this);

        initViews();
        setupReportFilter();
        loadReports();
        btnExportPdf.setOnClickListener(v -> exportPdf());
    }

    private void initViews() {

        txtTodaySales = findViewById(R.id.txtTodaySales);
        txtMonthSales = findViewById(R.id.txtMonthSales);
        txtRevenue = findViewById(R.id.txtRevenue);
        txtOrders = findViewById(R.id.txtOrders);
        txtTopProducts = findViewById(R.id.txtTopProducts);
        txtLowStock = findViewById(R.id.txtLowStock);

        txtTotalProducts = findViewById(R.id.txtTotalProducts);
        txtInventoryValue = findViewById(R.id.txtInventoryValue);
        txtLowStockCount = findViewById(R.id.txtLowStockCount);
        txtOutOfStock = findViewById(R.id.txtOutOfStock);
        txtPendingAmount = findViewById(R.id.txtPendingAmount);
        txtCustomerDue = findViewById(R.id.txtCustomerDue);
        spinnerReportFilter = findViewById(R.id.spinnerReportFilter);

        layoutTodaySales = findViewById(R.id.layoutTodaySales);
        layoutMonthSales = findViewById(R.id.layoutMonthSales);
        layoutRevenue = findViewById(R.id.layoutRevenue);
        layoutOrders = findViewById(R.id.layoutOrders);

        layoutCustomer = findViewById(R.id.layoutCustomer);
        layoutInventory = findViewById(R.id.layoutInventory);

        layoutTopProducts = findViewById(R.id.layoutTopProducts);
        layoutLowStock = findViewById(R.id.layoutLowStock);
        btnExportPdf = findViewById(R.id.btnExportPdf);
    }

    private void loadReports() {

        // ================= SALES REPORT =================
        txtTodaySales.setText("₹ " + db.getTodaySales(sessionManager.getUserId()));
        txtMonthSales.setText("₹ " + db.getMonthlySales(sessionManager.getUserId()));
        txtRevenue.setText("₹ " + db.getTotalRevenue(sessionManager.getUserId()));
        txtOrders.setText(String.valueOf(db.getTotalOrders(sessionManager.getUserId())));

        // ================= INVENTORY REPORT =================
        txtTotalProducts.setText(String.valueOf(db.getTotalProductsCount(sessionManager.getUserId())));
        txtInventoryValue.setText("₹ " + db.getInventoryValue(sessionManager.getUserId()));
        txtLowStockCount.setText(String.valueOf(db.getLowStockCount(sessionManager.getUserId())));
        txtOutOfStock.setText(String.valueOf(db.getOutOfStockCount(sessionManager.getUserId())));
        // ================= TOP SELLING PRODUCTS =================
        ArrayList<String> topProducts =
                db.getTopSellingProducts(sessionManager.getUserId());
        if (topProducts == null || topProducts.isEmpty()) {
            txtTopProducts.setText("No Orders Yet");
        } else {

            StringBuilder builder = new StringBuilder();

            for (String product : topProducts) {
                builder.append("• ")
                        .append(product)
                        .append("\n");
            }

            txtTopProducts.setText(builder.toString());
        }

        // ================= LOW STOCK PRODUCTS =================
        ArrayList<Product> lowStock =
                db.getLowStockProducts(sessionManager.getUserId());

        if (lowStock == null || lowStock.isEmpty()) {
            txtLowStock.setText("No Low Stock Products");
        } else {

            StringBuilder builder = new StringBuilder();

            for (Product product : lowStock) {

                builder.append("⚠ ")
                        .append(product.getProductName())
                        .append(" (")
                        .append(product.getQuantity())
                        .append(" ")
                        .append(product.getUnit())
                        .append(")\n");
            }

            txtLowStock.setText(builder.toString());
        }
// ================= CUSTOMER DUE REPORT =================

        txtPendingAmount.setText("₹ " + db.getTotalPendingAmount(sessionManager.getUserId()));
        ArrayList<String> dueList =
                db.getCustomerDueReport(sessionManager.getUserId());
        if (dueList.isEmpty()) {

            txtCustomerDue.setText("No Pending Customers");

        } else {

            StringBuilder builder = new StringBuilder();

            for (String customer : dueList) {

                builder.append("👤 ")
                        .append(customer)
                        .append("\n\n");
            }

            txtCustomerDue.setText(builder.toString());
        }
    }
    private void exportPdf() {

        PdfReportHelper.generateReport(

                this,

                txtTodaySales.getText().toString(),
                txtMonthSales.getText().toString(),
                txtRevenue.getText().toString(),
                txtOrders.getText().toString(),

                txtTotalProducts.getText().toString(),
                txtInventoryValue.getText().toString(),
                txtLowStockCount.getText().toString(),
                txtOutOfStock.getText().toString(),

                txtPendingAmount.getText().toString(),
                txtCustomerDue.getText().toString(),

                txtTopProducts.getText().toString(),
                txtLowStock.getText().toString()

        );
    }
    private void setupReportFilter() {

        String[] filter = {
                "All Reports",
                "Sales Reports",
                "Customer Due",
                "Inventory Reports"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        filter);

        spinnerReportFilter.setAdapter(adapter);

        spinnerReportFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                layoutTodaySales.setVisibility(View.GONE);
                layoutMonthSales.setVisibility(View.GONE);
                layoutRevenue.setVisibility(View.GONE);
                layoutOrders.setVisibility(View.GONE);

                layoutCustomer.setVisibility(View.GONE);

                layoutInventory.setVisibility(View.GONE);
                layoutTopProducts.setVisibility(View.GONE);
                layoutLowStock.setVisibility(View.GONE);

                switch (position) {

                    case 0:

                        layoutTodaySales.setVisibility(View.VISIBLE);
                        layoutMonthSales.setVisibility(View.VISIBLE);
                        layoutRevenue.setVisibility(View.VISIBLE);
                        layoutOrders.setVisibility(View.VISIBLE);

                        layoutCustomer.setVisibility(View.VISIBLE);

                        layoutInventory.setVisibility(View.VISIBLE);
                        layoutTopProducts.setVisibility(View.VISIBLE);
                        layoutLowStock.setVisibility(View.VISIBLE);

                        break;

                    case 1:

                        layoutTodaySales.setVisibility(View.VISIBLE);
                        layoutMonthSales.setVisibility(View.VISIBLE);
                        layoutRevenue.setVisibility(View.VISIBLE);
                        layoutOrders.setVisibility(View.VISIBLE);

                        break;

                    case 2:

                        layoutCustomer.setVisibility(View.VISIBLE);

                        break;

                    case 3:

                        layoutInventory.setVisibility(View.VISIBLE);
                        layoutTopProducts.setVisibility(View.VISIBLE);
                        layoutLowStock.setVisibility(View.VISIBLE);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
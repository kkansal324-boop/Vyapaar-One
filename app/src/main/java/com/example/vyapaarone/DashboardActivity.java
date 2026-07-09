package com.example.vyapaarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    ImageView imgProfile;
    TextView txtTitle;
    TextView txtSubtitle;

    DatabaseHelper db;
    Button btnAddProduct;
    Button btnViewProducts;
    Button btnCustomers;
    Button btnCustomerLedger;
    Button btnSuppliers;
    Button btnSupplierLedger;
    Button btnAddPurchase;
    Button btnPurchaseHistory;
    Button btnStockMovement;
    Button btnInventorySummary;
    Button btnOrders;
    Button btnBills;
    Button btnProfitLoss;
    Button btnReports;
    Button btnPurchaseReturn;
    Button btnBusinessProfile;
    Button btnChangePassword;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        db = new DatabaseHelper(this);
        imgProfile = findViewById(R.id.imgProfile);
        txtTitle = findViewById(R.id.txtTitle);
        txtSubtitle = findViewById(R.id.txtSubtitle);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnViewProducts = findViewById(R.id.btnViewProducts);
        btnInventorySummary = findViewById(R.id.btnInventorySummary);
        btnOrders = findViewById(R.id.btnOrders);
        btnBills = findViewById(R.id.btnBills);
        btnProfitLoss = findViewById(R.id.btnProfitLoss);
        btnPurchaseReturn = findViewById(R.id.btnPurchaseReturn);
        btnReports = findViewById(R.id.btnReports);
        btnBusinessProfile = findViewById(R.id.btnBusinessProfile);
        btnLogout = findViewById(R.id.btnLogout);
        btnCustomers = findViewById(R.id.btnCustomers);
        btnCustomerLedger = findViewById(R.id.btnCustomerLedger);
        btnSuppliers = findViewById(R.id.btnSuppliers);
        btnSupplierLedger = findViewById(R.id.btnSupplierLedger);
        btnAddPurchase = findViewById(R.id.btnAddPurchase);
        btnPurchaseHistory = findViewById(R.id.btnPurchaseHistory);
        btnStockMovement = findViewById(R.id.btnStockMovement);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        loadBusinessProfile();
        // Profile Page
        imgProfile.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // Add Product
        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AddProductActivity.class);
            startActivity(intent);
        });

        // View Products
        btnViewProducts.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ProductListActivity.class);
            startActivity(intent);
        });

        // Customers
        btnCustomers.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, CustomerListActivity.class);
            startActivity(intent);
        });
        // Customer Ledger
        btnCustomerLedger.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    CustomerLedgerActivity.class);

            startActivity(intent);

        });
        // Suppliers
        btnSuppliers.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    SupplierListActivity.class);

            startActivity(intent);

        });
        btnSupplierLedger.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            DashboardActivity.this,
                            SupplierLedgerActivity.class));

        });
        // Add Purchase
        btnAddPurchase.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    AddPurchaseActivity.class);

            startActivity(intent);

        });

// Purchase History
        btnPurchaseHistory.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    PurchaseHistoryActivity.class);

            startActivity(intent);

        });
        btnPurchaseReturn.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    AddPurchaseReturnActivity.class);

            startActivity(intent);

        });
        btnStockMovement.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    StockMovementActivity.class
            );

            startActivity(intent);

        });
        // Inventory Summary
        btnInventorySummary.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, InventorySummaryActivity.class);
            startActivity(intent);
        });

        // Orders
        btnOrders.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, OrderListActivity.class);
            startActivity(intent);
        });

        // Bills
        // Bills
        btnBills.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    BillHistoryActivity.class);

            startActivity(intent);

        });
        btnProfitLoss.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            DashboardActivity.this,
                            ProfitLossActivity.class));

        });
        // Reports
        btnReports.setOnClickListener(v -> {

            Intent intent = new Intent(DashboardActivity.this, ReportsActivity.class);
            startActivity(intent);

        });
        btnBusinessProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    BusinessProfileActivity.class);

            startActivity(intent);

        });
        btnChangePassword.setOnClickListener(v -> {

            startActivity(new Intent(
                    DashboardActivity.this,
                    ChangePasswordActivity.class));

        });

        // Logout
        SessionManager sessionManager =
                new SessionManager(this);

        btnLogout.setOnClickListener(v -> {

            sessionManager.logout();

            Intent intent =
                    new Intent(
                            DashboardActivity.this,
                            LoginActivity.class);

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);

            finish();

        });
    }
    private void loadBusinessProfile() {

        BusinessProfile profile = db.getBusinessProfile();

        if (profile == null) {
            return;
        }

        if (profile.getBusinessName() != null &&
                !profile.getBusinessName().isEmpty()) {

            txtTitle.setText(profile.getBusinessName());
        }

        if (profile.getOwnerName() != null &&
                !profile.getOwnerName().isEmpty()) {

            txtSubtitle.setText(profile.getOwnerName());
        }

        if (profile.getProfileImage() != null &&
                !profile.getProfileImage().isEmpty()) {

            imgProfile.setImageURI(
                    android.net.Uri.parse(profile.getProfileImage()));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadBusinessProfile();
    }
}
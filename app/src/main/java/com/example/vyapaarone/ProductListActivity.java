package com.example.vyapaarone;

import android.view.View;
import android.widget.LinearLayout;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayout layoutNoProduct;
    private ImageButton btnBack;
    private Button btnSort, btnFilter;
    private TextInputEditText etSearch;

    private DatabaseHelper databaseHelper;
    private ArrayList<Product> productList;

    private SessionManager sessionManager;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        layoutNoProduct = findViewById(R.id.layoutNoProduct);
        btnBack = findViewById(R.id.btnBack);
        btnSort = findViewById(R.id.btnSort);
        btnFilter = findViewById(R.id.btnFilter);
        etSearch = findViewById(R.id.etSearch);
        layoutNoProduct = findViewById(R.id.layoutNoProduct);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);

        loadProducts();

        btnBack.setOnClickListener(v -> finish());

        btnSort.setOnClickListener(v -> showSortDialog());

        btnFilter.setOnClickListener(v -> showFilterDialog());

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (productAdapter != null) {
                    productAdapter.getFilter().filter(s.toString());
                    updateEmptyState();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    private void loadProducts() {

        try {

            productList =
                    databaseHelper.getAllProducts(
                            sessionManager.getUserId());

            if (productAdapter == null) {
                productAdapter = new ProductAdapter(this, productList);
                recyclerView.setAdapter(productAdapter);
            } else {
                productAdapter.updateList(productList);
            }

            updateEmptyState();

        } catch (Exception e) {

            Toast.makeText(this,
                    e.getClass().getSimpleName() + "\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }

    private void refreshList(ArrayList<Product> list) {

        productAdapter.updateList(list);

        if (etSearch != null && etSearch.getText() != null) {
            productAdapter.getFilter().filter(etSearch.getText().toString());
        }

        updateEmptyState();
    }

    private void updateEmptyState() {

        if (productAdapter == null || productAdapter.getItemCount() == 0) {

            recyclerView.setVisibility(View.GONE);
            layoutNoProduct.setVisibility(View.VISIBLE);

        } else {

            recyclerView.setVisibility(View.VISIBLE);
            layoutNoProduct.setVisibility(View.GONE);
        }
    }
    // -------------------------
    // SORT
    // -------------------------

    private void showSortDialog() {

        String[] options = {
                "Name (A-Z)",
                "Name (Z-A)",
                "Price (Low-High)",
                "Price (High-Low)",
                "Stock (Low-High)",
                "Stock (High-Low)"
        };

        new AlertDialog.Builder(this)
                .setTitle("Sort Products")
                .setItems(options, (dialog, which) -> {

                    switch (which) {

                        case 0:
                            refreshList(databaseHelper.getProductsByNameAsc(sessionManager.getUserId()));
                            break;

                        case 1:
                            refreshList(databaseHelper.getProductsByNameDesc(sessionManager.getUserId()));
                            break;

                        case 2:
                            refreshList(databaseHelper.getProductsByPriceAsc(sessionManager.getUserId()));
                            break;

                        case 3:
                            refreshList(databaseHelper.getProductsByPriceDesc(sessionManager.getUserId()));
                            break;

                        case 4:
                            refreshList(databaseHelper.getProductsByStockAsc(sessionManager.getUserId()));
                            break;

                        case 5:
                            refreshList(databaseHelper.getProductsByStockDesc(sessionManager.getUserId()));
                            break;
                    }

                })
                .show();
    }


    // -------------------------
    // FILTER
    // -------------------------

    private void showFilterDialog() {

        String[] options = {
                "All Products",
                "Low Stock",
                "Normal Stock",
                "Stock Full",
                "Overstock"
        };

        new AlertDialog.Builder(this)
                .setTitle("Filter Products")
                .setItems(options, (dialog, which) -> {

                    switch (which) {

                        case 0:
                            refreshList(databaseHelper.getAllProducts(sessionManager.getUserId()));
                            break;

                        case 1:
                            refreshList(databaseHelper.getLowStockProducts(sessionManager.getUserId()));
                            break;

                        case 2:
                            refreshList(databaseHelper.getNormalStockProducts(sessionManager.getUserId()));
                            break;

                        case 3:
                            refreshList(databaseHelper.getStockFullProducts(sessionManager.getUserId()));
                            break;

                        case 4:
                            refreshList(databaseHelper.getOverStockProducts(sessionManager.getUserId()));
                            break;
                    }

                })
                .show();
    }
}
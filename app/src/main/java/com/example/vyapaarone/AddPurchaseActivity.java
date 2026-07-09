package com.example.vyapaarone;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddPurchaseActivity extends AppCompatActivity {

    // Database
    private DatabaseHelper databaseHelper;
    private int purchaseId = -1;
    private boolean isEditMode = false;
    // UI
    private Spinner spSupplier;
    private Spinner spProduct;

    private EditText etQuantity;
    private EditText etPurchasePrice;
    private EditText etTotalAmount;
    private EditText etPurchaseDate;
    private EditText etNotes;

    private Button btnSavePurchase;

    // Lists
    private ArrayList<Supplier> supplierList;
    private ArrayList<Product> productList;
    private SessionManager sessionManager;
    // Spinner Names
    private ArrayList<String> supplierNames;
    private ArrayList<String> productNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        sessionManager = new SessionManager(this);
        databaseHelper = new DatabaseHelper(this);

        // Views

        spSupplier = findViewById(R.id.spSupplier);
        spProduct = findViewById(R.id.spProduct);

        etQuantity = findViewById(R.id.etQuantity);
        etPurchasePrice = findViewById(R.id.etPurchasePrice);
        etTotalAmount = findViewById(R.id.etTotalAmount);
        etPurchaseDate = findViewById(R.id.etPurchaseDate);
        etNotes = findViewById(R.id.etNotes);

        btnSavePurchase = findViewById(R.id.btnSavePurchase);

        // Load Spinner Data

        loadSuppliers();
        loadProducts();

        // Auto Total Calculation

        TextWatcher totalWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                calculateTotal();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        };

        etQuantity.addTextChangedListener(totalWatcher);
        etPurchasePrice.addTextChangedListener(totalWatcher);

        // Set Today's Date

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf =
                new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        etPurchaseDate.setText(sdf.format(calendar.getTime()));

        // Date Picker

        etPurchaseDate.setOnClickListener(v -> {

            Calendar c = Calendar.getInstance();

            DatePickerDialog dialog =
                    new DatePickerDialog(
                            AddPurchaseActivity.this,
                            (view, year, month, dayOfMonth) -> {

                                Calendar selectedDate = Calendar.getInstance();

                                selectedDate.set(year, month, dayOfMonth);

                                etPurchaseDate.setText(
                                        sdf.format(selectedDate.getTime()));

                            },
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH));

            dialog.show();

        });

        // ==========================
        // CHECK EDIT MODE
        // ==========================

        if (getIntent().hasExtra("purchase_id")) {

            purchaseId = getIntent().getIntExtra("purchase_id", -1);

            if (purchaseId != -1) {

                isEditMode = true;

                btnSavePurchase.setText("Update Purchase");

                loadPurchaseData();

            }

        }

        // ==========================
        // SAVE / UPDATE PURCHASE
        // ==========================

        btnSavePurchase.setOnClickListener(v -> {

            if (isEditMode) {

                updatePurchase();

            } else {

                savePurchase();

            }

        });

    }

    // ==============================
    // CALCULATE TOTAL
    // ==============================

    private void calculateTotal() {

        String qty = etQuantity.getText().toString().trim();

        String price = etPurchasePrice.getText().toString().trim();

        if (qty.isEmpty() || price.isEmpty()) {

            etTotalAmount.setText("");

            return;
        }

        double quantity = Double.parseDouble(qty);

        double purchasePrice = Double.parseDouble(price);

        double total = quantity * purchasePrice;

        etTotalAmount.setText(String.format(Locale.getDefault(),"%.2f", total));

    }
    // ==============================
    // SAVE PURCHASE
    // ==============================
    private void savePurchase() {

        if (supplierList.isEmpty()) {
            Toast.makeText(this, "No Supplier Available", Toast.LENGTH_SHORT).show();
            return;
        }

        if (productList.isEmpty()) {
            Toast.makeText(this, "No Product Available", Toast.LENGTH_SHORT).show();
            return;
        }

        String quantityText = etQuantity.getText().toString().trim();
        String priceText = etPurchasePrice.getText().toString().trim();

        if (quantityText.isEmpty()) {
            etQuantity.setError("Enter Quantity");
            etQuantity.requestFocus();
            return;
        }

        if (priceText.isEmpty()) {
            etPurchasePrice.setError("Enter Purchase Price");
            etPurchasePrice.requestFocus();
            return;
        }

        double quantity = Double.parseDouble(quantityText);
        double purchasePrice = Double.parseDouble(priceText);
        double totalAmount = quantity * purchasePrice;

        Supplier supplier =
                supplierList.get(spSupplier.getSelectedItemPosition());

        Product product =
                productList.get(spProduct.getSelectedItemPosition());

        Purchase purchase = new Purchase();

        purchase.setSupplierId(supplier.getSupplierId());
        purchase.setProductId(product.getId());
        purchase.setQuantity(quantity);
        purchase.setPurchasePrice(purchasePrice);
        purchase.setTotalAmount(totalAmount);
        purchase.setPurchaseDate(etPurchaseDate.getText().toString().trim());
        purchase.setNotes(etNotes.getText().toString().trim());

        // ===========================
        // EDIT PURCHASE
        // ===========================

        if (purchaseId != -1) {

            Purchase oldPurchase =
                    databaseHelper.getPurchaseById(
                            purchaseId,
                            sessionManager.getUserId());

            if (oldPurchase != null) {

                databaseHelper.decreaseProductStock(
                        oldPurchase.getProductId(),
                        oldPurchase.getQuantity());

            }

            purchase.setPurchaseId(purchaseId);

            boolean updated =
                    databaseHelper.updatePurchase(purchase);

            if (updated) {

                databaseHelper.increaseProductStock(
                        product.getId(),
                        quantity);

                Toast.makeText(
                        this,
                        "Purchase Updated Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            } else {

                Toast.makeText(
                        this,
                        "Failed To Update Purchase",
                        Toast.LENGTH_SHORT
                ).show();

            }

            return;
        }

        // ===========================
        // NEW PURCHASE
        // ===========================

        boolean purchaseSaved =
                databaseHelper.insertPurchase(
                        purchase,
                        sessionManager.getUserId()
                );
        if (purchaseSaved) {

            boolean stockUpdated =
                    databaseHelper.increaseProductStock(
                            product.getId(),
                            quantity);
            Product updatedProduct = databaseHelper.getProductById(product.getId());

            StockMovement movement = new StockMovement(
                    product.getId(),
                    product.getProductName(),
                    "PURCHASE",
                    quantity,
                    updatedProduct.getQuantity(),
                    etPurchaseDate.getText().toString().trim()
            );

            databaseHelper.insertStockMovement(movement);
            if (stockUpdated) {

                Toast.makeText(
                        this,
                        "Purchase Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                clearFields();

            } else {

                Toast.makeText(
                        this,
                        "Purchase Saved But Stock Not Updated",
                        Toast.LENGTH_LONG
                ).show();

            }

        } else {

            Toast.makeText(
                    this,
                    "Failed To Save Purchase",
                    Toast.LENGTH_SHORT
            ).show();

        }

    }
    // ==============================
    // CLEAR FORM
    // ==============================

    private void clearFields() {

        spSupplier.setSelection(0);

        spProduct.setSelection(0);

        etQuantity.setText("");

        etPurchasePrice.setText("");

        etTotalAmount.setText("");

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf =
                new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        etPurchaseDate.setText(sdf.format(calendar.getTime()));

        etNotes.setText("");

        etQuantity.requestFocus();

    }

    // ==============================
    // LOAD SUPPLIERS
    // ==============================

    private void loadSuppliers() {

        supplierList = databaseHelper.getAllSuppliers(sessionManager.getUserId());
        supplierNames = new ArrayList<>();

        for (Supplier supplier : supplierList) {

            supplierNames.add(supplier.getSupplierName());

        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        supplierNames);

        spSupplier.setAdapter(adapter);

    }

    // ==============================
    // LOAD PRODUCTS
    // ==============================

    private void loadProducts() {

        productList = databaseHelper.getAllProducts(sessionManager.getUserId());

        productNames = new ArrayList<>();

        for (Product product : productList) {

            productNames.add(product.getProductName());

        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        productNames);

        spProduct.setAdapter(adapter);

    }
// ==========================
// LOAD PURCHASE DATA
// ==========================

    private void loadPurchaseData() {

        Purchase purchase =
                databaseHelper.getPurchaseById(
                        purchaseId,
                        sessionManager.getUserId());
        if (purchase == null) {
            return;
        }

        // Supplier Spinner
        for (int i = 0; i < supplierList.size(); i++) {

            if (supplierList.get(i).getSupplierId()
                    == purchase.getSupplierId()) {

                spSupplier.setSelection(i);
                break;

            }

        }

        // Product Spinner
        for (int i = 0; i < productList.size(); i++) {

            if (productList.get(i).getId()
                    == purchase.getProductId()) {

                spProduct.setSelection(i);
                break;

            }

        }

        etQuantity.setText(String.valueOf(purchase.getQuantity()));

        etPurchasePrice.setText(
                String.valueOf(purchase.getPurchasePrice()));

        etTotalAmount.setText(
                String.valueOf(purchase.getTotalAmount()));

        etPurchaseDate.setText(
                purchase.getPurchaseDate());

        etNotes.setText(
                purchase.getNotes());

    }
    // ==========================
// UPDATE PURCHASE
// ==========================

    private void updatePurchase() {

        if (supplierList.isEmpty() || productList.isEmpty()) {
            Toast.makeText(this, "Supplier/Product not available", Toast.LENGTH_SHORT).show();
            return;
        }

        String quantityText = etQuantity.getText().toString().trim();
        String priceText = etPurchasePrice.getText().toString().trim();

        if (quantityText.isEmpty()) {
            etQuantity.setError("Enter Quantity");
            etQuantity.requestFocus();
            return;
        }

        if (priceText.isEmpty()) {
            etPurchasePrice.setError("Enter Purchase Price");
            etPurchasePrice.requestFocus();
            return;
        }

        double quantity = Double.parseDouble(quantityText);
        double purchasePrice = Double.parseDouble(priceText);
        double totalAmount = quantity * purchasePrice;

        Supplier supplier =
                supplierList.get(spSupplier.getSelectedItemPosition());

        Product product =
                productList.get(spProduct.getSelectedItemPosition());

        Purchase purchase = new Purchase();

        purchase.setPurchaseId(purchaseId);
        purchase.setSupplierId(supplier.getSupplierId());
        purchase.setProductId(product.getId());
        purchase.setQuantity(quantity);
        purchase.setPurchasePrice(purchasePrice);
        purchase.setTotalAmount(totalAmount);
        purchase.setPurchaseDate(etPurchaseDate.getText().toString().trim());
        purchase.setNotes(etNotes.getText().toString().trim());

        boolean result = databaseHelper.updatePurchase(purchase);

        if (result) {
            Product updatedProduct =
                    databaseHelper.getProductById(product.getId());

            StockMovement movement =
                    new StockMovement(
                            product.getId(),
                            product.getProductName(),
                            "PURCHASE UPDATE",
                            quantity,
                            updatedProduct.getQuantity(),
                            etPurchaseDate.getText().toString().trim()
                    );

            databaseHelper.insertStockMovement(movement);

            Toast.makeText(
                    this,
                    "Purchase Updated Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Failed To Update Purchase",
                    Toast.LENGTH_SHORT
            ).show();

        }

    }
}
package com.example.vyapaarone;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView txtProductName;
    private TextView txtQuantity;
    private TextView txtUnit;
    private TextView txtPrice;
    private TextView txtTotalPrice;
    private TextView txtMinStock;
    private TextView txtMaxStock;
    private TextView txtStockStatus;

    private ImageButton btnBack;

    private Button btnEdit;
    private Button btnIncrease;
    private Button btnDecrease;
    private Button btnDelete;

    private DatabaseHelper databaseHelper;

    private int productId;
    private Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        txtProductName = findViewById(R.id.txtProductName);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtUnit = findViewById(R.id.txtUnit);
        txtPrice = findViewById(R.id.txtPrice);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtMinStock = findViewById(R.id.txtMinStock);
        txtMaxStock = findViewById(R.id.txtMaxStock);
        txtStockStatus = findViewById(R.id.txtStockStatus);

        btnBack = findViewById(R.id.btnBack);

        btnEdit = findViewById(R.id.btnEdit);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnDelete = findViewById(R.id.btnDelete);

        databaseHelper = new DatabaseHelper(this);

        btnBack.setOnClickListener(v -> finish());

        productId = getIntent().getIntExtra("productId", -1);

        if (productId != -1) {
            loadProduct(productId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (productId != -1) {
            loadProduct(productId);
        }
    }

    private void loadProduct(int productId) {

        currentProduct = databaseHelper.getProductById(productId);

        if (currentProduct == null)
            return;

        txtProductName.setText(
                "📦 Product Name : " + currentProduct.getProductName());

        txtQuantity.setText(
                "🔢 Quantity : "
                        + currentProduct.getQuantity()
                        + " "
                        + currentProduct.getUnit());

        txtUnit.setText(
                "📏 Unit : "
                        + currentProduct.getUnit());

        txtPrice.setText(
                "💰 Price / Unit : ₹"
                        + currentProduct.getPricePerUnit());

        txtTotalPrice.setText(
                "💵 Total Price : ₹"
                        + currentProduct.getTotalPrice());

        txtMinStock.setText(
                "📉 Minimum Stock : "
                        + currentProduct.getMinStock());

        txtMaxStock.setText(
                "📈 Maximum Stock : "
                        + currentProduct.getMaxStock());

        double quantity = currentProduct.getQuantity();

        if (quantity < currentProduct.getMinStock()) {

            txtStockStatus.setText("🟡 Stock Status : Low Stock");

        } else if (quantity == currentProduct.getMaxStock()) {

            txtStockStatus.setText("🔵 Stock Status : Stock Full");

        } else if (quantity > currentProduct.getMaxStock()) {

            txtStockStatus.setText("🔴 Stock Status : Overstock");

        } else {

            txtStockStatus.setText("🟢 Stock Status : Normal");
        }

        // ===============================
// EDIT PRODUCT
// ===============================

        btnEdit.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ProductDetailsActivity.this,
                    AddProductActivity.class);

            intent.putExtra("productId", currentProduct.getId());
            intent.putExtra("productName", currentProduct.getProductName());
            intent.putExtra("quantity", currentProduct.getQuantity());
            intent.putExtra("unit", currentProduct.getUnit());
            intent.putExtra("pricePerUnit", currentProduct.getPricePerUnit());
            intent.putExtra("sellingPrice", currentProduct.getSellingPrice());
            intent.putExtra("totalPrice", currentProduct.getTotalPrice());
            intent.putExtra("minStock", currentProduct.getMinStock());
            intent.putExtra("maxStock", currentProduct.getMaxStock());

            startActivity(intent);

        });

// ===============================
// INCREASE STOCK
// ===============================

        btnIncrease.setOnClickListener(v -> {

            showIncreaseStockDialog();

        });

// ===============================
// REDUCE STOCK
// ===============================

        btnDecrease.setOnClickListener(v -> {

            showReduceStockDialog();

        });

// ===============================
// DELETE PRODUCT
// ===============================

        btnDelete.setOnClickListener(v -> {

            deleteProduct();
        });
    }
// =====================================
// INCREASE STOCK
// =====================================

    private void showIncreaseStockDialog() {

        EditText input = new EditText(this);
        input.setHint("Enter stock to add");

        new AlertDialog.Builder(this)
                .setTitle("Increase Stock")
                .setView(input)

                .setPositiveButton("Add", (dialog, which) -> {

                    String value = input.getText().toString().trim();

                    if (value.isEmpty()) {
                        Toast.makeText(this,
                                "Enter quantity",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double add = Double.parseDouble(value);

                    double newStock =
                            currentProduct.getQuantity() + add;

                    boolean success =
                            databaseHelper.updateStock(
                                    currentProduct.getId(),
                                    newStock);

                    if (success) {

                        loadProduct(productId);

                        Toast.makeText(
                                this,
                                "Stock Increased Successfully",
                                Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(
                                this,
                                "Failed",
                                Toast.LENGTH_SHORT).show();
                    }

                })

                .setNegativeButton("Cancel", null)

                .show();
    }

// =====================================
// REDUCE STOCK
// =====================================

    private void showReduceStockDialog() {

        EditText input = new EditText(this);
        input.setHint("Enter stock to reduce");

        new AlertDialog.Builder(this)
                .setTitle("Reduce Stock")
                .setView(input)

                .setPositiveButton("Reduce", (dialog, which) -> {

                    String value = input.getText().toString().trim();

                    if (value.isEmpty()) {

                        Toast.makeText(
                                this,
                                "Enter quantity",
                                Toast.LENGTH_SHORT).show();

                        return;
                    }

                    double reduce =
                            Double.parseDouble(value);

                    if (reduce > currentProduct.getQuantity()) {

                        Toast.makeText(
                                this,
                                "Insufficient Stock",
                                Toast.LENGTH_SHORT).show();

                        return;
                    }

                    double newStock =
                            currentProduct.getQuantity() - reduce;

                    boolean success =
                            databaseHelper.updateStock(
                                    currentProduct.getId(),
                                    newStock);

                    if (success) {

                        loadProduct(productId);

                        Toast.makeText(
                                this,
                                "Stock Reduced Successfully",
                                Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(
                                this,
                                "Failed",
                                Toast.LENGTH_SHORT).show();
                    }

                })

                .setNegativeButton("Cancel", null)

                .show();
    }

// =====================================
// DELETE PRODUCT
// =====================================

    private void deleteProduct() {

        new AlertDialog.Builder(this)

                .setTitle("Delete Product")

                .setMessage("Are you sure you want to delete this product?")

                .setPositiveButton("Delete", (dialog, which) -> {

                    databaseHelper.deleteProduct(currentProduct.getId());

                    Toast.makeText(
                            this,
                            "Product Deleted Successfully",
                            Toast.LENGTH_SHORT).show();

                    finish();

                })

                .setNegativeButton("Cancel", null)

                .show();
    }
}

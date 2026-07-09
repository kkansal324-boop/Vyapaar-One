package com.example.vyapaarone;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    EditText etProductName, etQuantity, etPricePerUnit, etTotalPrice;
    EditText etSellingPrice;
    EditText etMinStock, etMaxStock;
    Spinner spinnerUnit;
    Button btnSave, btnReset;

    DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    boolean isEdit = false;
    int productId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etProductName = findViewById(R.id.etProductName);
        etQuantity = findViewById(R.id.etQuantity);
        etPricePerUnit = findViewById(R.id.etPricePerUnit);
        etSellingPrice = findViewById(R.id.etSellingPrice);
        etTotalPrice = findViewById(R.id.etTotalPrice);

        // NEW
        etMinStock = findViewById(R.id.etMinStock);
        etMaxStock = findViewById(R.id.etMaxStock);

        spinnerUnit = findViewById(R.id.spinnerUnit);

        btnSave = findViewById(R.id.btnSave);
        btnReset = findViewById(R.id.btnReset);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.units,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerUnit.setAdapter(adapter);

        // Auto Calculate Total Price
        TextWatcher watcher = new TextWatcher() {
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

        etQuantity.addTextChangedListener(watcher);
        etPricePerUnit.addTextChangedListener(watcher);

        // Edit Mode
        if (getIntent().hasExtra("productId")) {

            isEdit = true;

            productId = getIntent().getIntExtra("productId", -1);

            etProductName.setText(getIntent().getStringExtra("productName"));

            etQuantity.setText(
                    String.valueOf(
                            getIntent().getDoubleExtra("quantity", 0)));

            etPricePerUnit.setText(
                    String.valueOf(
                            getIntent().getDoubleExtra("pricePerUnit", 0)));
            etSellingPrice.setText(
                    String.valueOf(
                            getIntent().getDoubleExtra("sellingPrice", 0)));

            // NEW
            etMinStock.setText(
                    String.valueOf(
                            getIntent().getDoubleExtra("minStock", 0)));

            etMaxStock.setText(
                    String.valueOf(
                            getIntent().getDoubleExtra("maxStock", 0)));

            String unit = getIntent().getStringExtra("unit");

            for (int i = 0; i < spinnerUnit.getCount(); i++) {

                if (spinnerUnit.getItemAtPosition(i).toString().equals(unit)) {

                    spinnerUnit.setSelection(i);
                    break;
                }
            }

            btnSave.setText("UPDATE PRODUCT");
        }

        // Save / Update
        btnSave.setOnClickListener(v -> {

            String name = etProductName.getText().toString().trim();
            String qtyText = etQuantity.getText().toString().trim();
            String priceText = etPricePerUnit.getText().toString().trim();
            String sellingPriceText = etSellingPrice.getText().toString().trim();
            String minText = etMinStock.getText().toString().trim();
            String maxText = etMaxStock.getText().toString().trim();

            if (name.isEmpty()
                    || qtyText.isEmpty()
                    || priceText.isEmpty()
                    || sellingPriceText.isEmpty()
                    || minText.isEmpty()
                    || maxText.isEmpty())  {

                Toast.makeText(
                        this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            double quantity = Double.parseDouble(qtyText);
            double pricePerUnit = Double.parseDouble(priceText);
            double sellingPrice = Double.parseDouble(sellingPriceText);
            double totalPrice = quantity * pricePerUnit;

            double minStock = Double.parseDouble(minText);
            double maxStock = Double.parseDouble(maxText);

            if (minStock > maxStock) {

                Toast.makeText(
                        this,
                        "Minimum Stock cannot be greater than Maximum Stock",
                        Toast.LENGTH_LONG).show();

                return;
            }

            Product product = new Product(
                    name,
                    quantity,
                    spinnerUnit.getSelectedItem().toString(),
                    pricePerUnit,
                    sellingPrice,
                    totalPrice,
                    minStock,
                    maxStock
            );

            if (isEdit) {

                product.setId(productId);

                boolean updated =
                        databaseHelper.updateProduct(product);

                if (updated) {

                    Toast.makeText(
                            this,
                            "Product Updated Successfully",
                            Toast.LENGTH_SHORT).show();

                    finish();

                } else {

                    Toast.makeText(
                            this,
                            "Update Failed",
                            Toast.LENGTH_SHORT).show();
                }

            } else {

                int userId = sessionManager.getUserId();

                boolean inserted =
                        databaseHelper.insertProduct(product, userId);

                if (inserted) {

                    Toast.makeText(
                            this,
                            "Product Saved Successfully",
                            Toast.LENGTH_SHORT).show();

                    clearFields();

                } else {

                    Toast.makeText(
                            this,
                            "Save Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnReset.setOnClickListener(v -> clearFields());

    }

    private void calculateTotal() {

        String qty = etQuantity.getText().toString();
        String price = etPricePerUnit.getText().toString();

        if (qty.isEmpty() || price.isEmpty()) {

            etTotalPrice.setText("");
            return;
        }

        double q = Double.parseDouble(qty);
        double p = Double.parseDouble(price);

        double total = q * p;

        etTotalPrice.setText(String.valueOf(total));
    }

    private void clearFields() {

        etProductName.setText("");
        etQuantity.setText("");
        etPricePerUnit.setText("");
        etSellingPrice.setText("");
        etTotalPrice.setText("");

        // NEW
        etMinStock.setText("");
        etMaxStock.setText("");

        spinnerUnit.setSelection(0);
    }
}
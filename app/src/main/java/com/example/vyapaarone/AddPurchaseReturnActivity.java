package com.example.vyapaarone;

import android.os.Bundle;
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

public class AddPurchaseReturnActivity extends AppCompatActivity {

    Spinner spPurchase;

    EditText etReturnQuantity;
    EditText etReturnAmount;
    EditText etReturnDate;
    EditText etReason;

    Button btnSaveReturn;
    private SessionManager sessionManager;
    DatabaseHelper databaseHelper;

    ArrayList<Purchase> purchaseList;
    ArrayList<String> purchaseNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase_return);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        spPurchase = findViewById(R.id.spPurchase);

        etReturnQuantity = findViewById(R.id.etReturnQuantity);
        etReturnAmount = findViewById(R.id.etReturnAmount);
        etReturnDate = findViewById(R.id.etReturnDate);
        etReason = findViewById(R.id.etReason);

        btnSaveReturn = findViewById(R.id.btnSaveReturn);

        loadPurchases();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf =
                new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        etReturnDate.setText(sdf.format(calendar.getTime()));

        btnSaveReturn.setOnClickListener(v -> saveReturn());

    }

    private void loadPurchases() {

        purchaseList =
                databaseHelper.getAllPurchases(
                        sessionManager.getUserId());
        purchaseNames = new ArrayList<>();

        for (Purchase purchase : purchaseList) {

            purchaseNames.add(
                    "Purchase #" + purchase.getPurchaseId());

        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        purchaseNames);

        spPurchase.setAdapter(adapter);

    }

    private void saveReturn() {

        if (purchaseList.isEmpty()) {

            Toast.makeText(
                    this,
                    "No Purchase Found",
                    Toast.LENGTH_SHORT).show();

            return;

        }

        String qtyText =
                etReturnQuantity.getText().toString().trim();

        String amountText =
                etReturnAmount.getText().toString().trim();

        if (qtyText.isEmpty()) {

            etReturnQuantity.setError("Enter Quantity");

            return;

        }

        if (amountText.isEmpty()) {

            etReturnAmount.setError("Enter Amount");

            return;

        }

        Purchase purchase =
                purchaseList.get(
                        spPurchase.getSelectedItemPosition());

        PurchaseReturn purchaseReturn =
                new PurchaseReturn();

        purchaseReturn.setPurchaseId(
                purchase.getPurchaseId());

        purchaseReturn.setSupplierId(
                purchase.getSupplierId());

        purchaseReturn.setProductId(
                purchase.getProductId());

        purchaseReturn.setReturnQuantity(
                Double.parseDouble(qtyText));

        purchaseReturn.setReturnAmount(
                Double.parseDouble(amountText));

        purchaseReturn.setReturnDate(
                etReturnDate.getText().toString());

        purchaseReturn.setReason(
                etReason.getText().toString());

        boolean inserted =
                databaseHelper.insertPurchaseReturn(
                        purchaseReturn,
                        sessionManager.getUserId());

        if (inserted) {

            databaseHelper.decreaseProductStock(
                    purchase.getProductId(),
                    Double.parseDouble(qtyText));
            Product product =
                    databaseHelper.getProductById(purchaseReturn.getProductId());

            StockMovement movement =
                    new StockMovement(
                            product.getId(),
                            product.getProductName(),
                            "PURCHASE RETURN",
                            purchaseReturn.getReturnQuantity(),
                            product.getQuantity(),
                            purchaseReturn.getReturnDate()
                    );

            databaseHelper.insertStockMovement(movement);

            Toast.makeText(
                    this,
                    "Purchase Return Saved",
                    Toast.LENGTH_SHORT).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Failed",
                    Toast.LENGTH_SHORT).show();

        }

    }

}
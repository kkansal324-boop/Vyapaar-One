package com.example.vyapaarone;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddOrderActivity extends AppCompatActivity {

    Spinner spinnerCustomer, spinnerProduct;
    EditText edtQuantity;
    Button btnAddItem, btnSaveOrder;
    TextView txtTotal;
    private SessionManager sessionManager;
    DatabaseHelper db;

    ArrayList<Customer> customerList;
    ArrayList<Product> productList;

    ArrayList<OrderItem> cartList = new ArrayList<>();

    double totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        sessionManager = new SessionManager(this);
        db = new DatabaseHelper(this);

        spinnerCustomer = findViewById(R.id.spinnerCustomer);
        spinnerProduct = findViewById(R.id.spinnerProduct);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnAddItem = findViewById(R.id.btnAddItem);
        btnSaveOrder = findViewById(R.id.btnSaveOrder);
        txtTotal = findViewById(R.id.txtTotal);

        loadCustomers();
        loadProducts();

        btnAddItem.setOnClickListener(v -> addItemToCart());

        btnSaveOrder.setOnClickListener(v -> saveOrder());
    }

    // =========================
    // LOAD CUSTOMERS
    // =========================
    private void loadCustomers() {

        customerList = db.getAllCustomers();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item
        );

        for (Customer c : customerList) {
            adapter.add(c.getCustomerName());
        }

        spinnerCustomer.setAdapter(adapter);
    }

    // =========================
    // LOAD PRODUCTS
    // =========================
    private void loadProducts() {

        productList = db.getAllProducts(sessionManager.getUserId());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item
        );

        for (Product p : productList) {
            adapter.add(p.getProductName());
        }

        spinnerProduct.setAdapter(adapter);
    }
    // =========================
// ADD ITEM TO CART
// =========================
    private void addItemToCart() {

        int productPos = spinnerProduct.getSelectedItemPosition();

        if (productPos < 0 || edtQuantity.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Enter valid quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = productList.get(productPos);

        double qty = Double.parseDouble(edtQuantity.getText().toString());

        if (qty <= 0) {
            Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        if (qty > product.getQuantity()) {
            Toast.makeText(this, "Not enough stock", Toast.LENGTH_SHORT).show();
            return;
        }

        // Store UNIT PRICE
        double unitPrice = product.getSellingPrice();

        OrderItem item = new OrderItem(
                0,
                0,
                product.getId(),
                product.getProductName(),
                qty,
                unitPrice
        );

        cartList.add(item);

        // Calculate total separately
        totalAmount += qty * unitPrice;

        txtTotal.setText("Total : ₹ " + totalAmount);

        edtQuantity.setText("");

        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
    }
    // =========================
// SAVE ORDER
// =========================
    private void saveOrder() {

        if (cartList.isEmpty()) {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        int customerPos = spinnerCustomer.getSelectedItemPosition();
        Customer customer = customerList.get(customerPos);

        String date = new SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
        ).format(new Date());

        Order order = new Order(
                customer.getCustomerId(),
                customer.getCustomerName(),
                date,
                totalAmount,
                "CONFIRMED"
        );

        long orderId =
                db.insertOrder(
                        order,
                        sessionManager.getUserId());
        if (orderId == -1) {
            Toast.makeText(this, "Order Failed", Toast.LENGTH_SHORT).show();
            return;
        }

        for (OrderItem item : cartList) {

            item.setOrderId((int) orderId);

            db.insertOrderItem(
                    item,
                    sessionManager.getUserId());

            Product p = db.getProductById(item.getProductId());

            double newQty = p.getQuantity() - item.getQuantity();

            db.updateStock(p.getId(), newQty);
            StockMovement movement =
                    new StockMovement(
                            p.getId(),
                            p.getProductName(),
                            "SALE",
                            item.getQuantity(),
                            newQty,
                            date
                    );

            db.insertStockMovement(movement);

        }

        Toast.makeText(this, "Order Created Successfully", Toast.LENGTH_LONG).show();

        finish();
    }
}
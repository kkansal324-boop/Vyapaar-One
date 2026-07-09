package com.example.vyapaarone;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView txtCustomerName;
    private TextView txtOrderDate;
    private TextView txtTotalAmount;
    private TextView txtStatus;

    private RecyclerView recyclerOrderItems;

    private Button btnEditOrder;
    private Button btnDeleteOrder;
    private Button btnAddPayment;
    private Button btnViewInvoice;

    private ImageButton btnBack;

    private DatabaseHelper db;

    private int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // TextViews
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtOrderDate = findViewById(R.id.txtOrderDate);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        txtStatus = findViewById(R.id.txtStatus);

        // RecyclerView
        recyclerOrderItems = findViewById(R.id.recyclerOrderItems);
        recyclerOrderItems.setLayoutManager(new LinearLayoutManager(this));

        // Buttons
        btnEditOrder = findViewById(R.id.btnEditOrder);
        btnDeleteOrder = findViewById(R.id.btnDeleteOrder);
        btnAddPayment = findViewById(R.id.btnAddPayment);
        btnViewInvoice = findViewById(R.id.btnViewInvoice);
        btnBack = findViewById(R.id.btnBack);

        db = new DatabaseHelper(this);

        orderId = getIntent().getIntExtra("order_id", -1);

        if (orderId == -1) {
            Toast.makeText(this, "Invalid Order", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadOrder();

        btnBack.setOnClickListener(v -> finish());

        btnEditOrder.setOnClickListener(v -> {
            Intent intent = new Intent(OrderDetailsActivity.this, EditOrderActivity.class);
            intent.putExtra("order_id", orderId);
            startActivity(intent);
        });

        btnAddPayment.setOnClickListener(v -> {
            Intent intent = new Intent(OrderDetailsActivity.this, AddPaymentActivity.class);
            intent.putExtra("order_id", orderId);
            startActivity(intent);
        });

        btnViewInvoice.setOnClickListener(v -> {
            Intent intent = new Intent(OrderDetailsActivity.this, InvoiceActivity.class);
            intent.putExtra("order_id", orderId);
            startActivity(intent);
        });

        btnDeleteOrder.setOnClickListener(v -> {

            if (db.hasActiveInvoice(orderId)) {

                Toast.makeText(
                        this,
                        "Cancel the invoice before deleting this order.",
                        Toast.LENGTH_LONG
                ).show();

                return;
            }

            confirmDelete();
        });
    }
    private void loadOrder() {

        Order order = db.getOrderById(orderId);

        if (order == null) {

            Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        txtCustomerName.setText("Customer : " + order.getCustomerName());
        txtOrderDate.setText("Date : " + order.getOrderDate());
        txtTotalAmount.setText("Total : ₹ " + order.getTotalAmount());
        txtStatus.setText("Status : " + order.getOrderStatus());

        ArrayList<OrderItem> items = db.getOrderItemsByOrderId(orderId);

        OrderItemAdapter adapter = new OrderItemAdapter(items);

        recyclerOrderItems.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadOrder();
    }



    private void confirmDelete() {

        boolean invoiceExists = db.hasActiveInvoice(orderId);
        boolean paymentExists = db.hasPayment(orderId);

        if (invoiceExists && paymentExists) {

            new AlertDialog.Builder(this)
                    .setTitle("Cannot Delete")
                    .setMessage(
                            "This order already has an invoice and payment.\n\n" +
                                    "Please cancel the invoice instead."
                    )
                    .setPositiveButton("OK", null)
                    .show();

            return;
        }

        String message;

        if (invoiceExists) {

            message =
                    "This order already has an invoice.\n\n" +
                            "Deleting it will also delete the invoice.\n\n" +
                            "Do you want to continue?";

        } else {

            message =
                    "Delete this order?\n\n" +
                            "Stock will be restored.";

        }

        new AlertDialog.Builder(this)
                .setTitle("Delete Order")
                .setMessage(message)
                .setPositiveButton("Delete", (dialog, which) -> {

                    if (db.deleteOrder(orderId)) {

                        Toast.makeText(
                                OrderDetailsActivity.this,
                                "Order Deleted Successfully",
                                Toast.LENGTH_SHORT
                        ).show();

                        finish();

                    } else {

                        Toast.makeText(
                                OrderDetailsActivity.this,
                                "Delete Failed",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
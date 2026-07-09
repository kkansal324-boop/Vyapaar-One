package com.example.vyapaarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectPendingOrderActivity extends AppCompatActivity {

    private TextView txtTitle;
    private ListView listOrders;

    private DatabaseHelper db;

    private int customerId;

    private ArrayList<Order> orderList;
    private ArrayList<String> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pending_order);

        txtTitle = findViewById(R.id.txtTitle);
        listOrders = findViewById(R.id.listOrders);

        db = new DatabaseHelper(this);

        customerId = getIntent().getIntExtra("customer_id", -1);

        if (customerId == -1) {
            Toast.makeText(this, "Invalid Customer", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadOrders();
    }

    private void loadOrders() {

        orderList = db.getPendingOrdersByCustomer(customerId);

        if (orderList.isEmpty()) {

            Toast.makeText(this,
                    "No Pending Orders",
                    Toast.LENGTH_SHORT).show();

            finish();
            return;
        }

        displayList = new ArrayList<>();

        for (Order order : orderList) {

            double paid = db.getTotalPaidAmount(order.getOrderId());

            double pending = order.getTotalAmount() - paid;

            displayList.add(
                    "Order #" + order.getOrderId()
                            + "\nDate : " + order.getOrderDate()
                            + "\nPending : ₹" + pending
            );
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        displayList);

        listOrders.setAdapter(adapter);

        listOrders.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(
                    SelectPendingOrderActivity.this,
                    AddPaymentActivity.class);

            intent.putExtra(
                    "order_id",
                    orderList.get(position).getOrderId());

            startActivity(intent);

            finish();
        });
    }
}
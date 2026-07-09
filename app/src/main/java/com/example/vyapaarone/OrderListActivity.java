package com.example.vyapaarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    private RecyclerView recyclerOrders;
    private ImageButton btnBack;
    private Button btnAddOrder;

    private DatabaseHelper databaseHelper;
    private ArrayList<Order> orderList;
    private SessionManager sessionManager;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        recyclerOrders = findViewById(R.id.recyclerOrders);
        btnBack = findViewById(R.id.btnBack);
        btnAddOrder = findViewById(R.id.btnAddOrder);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));

        loadOrders();

        btnBack.setOnClickListener(v -> finish());

        btnAddOrder.setOnClickListener(v -> {
            Intent intent = new Intent(OrderListActivity.this, AddOrderActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrders();
    }

    private void loadOrders() {

        orderList =
                databaseHelper.getAllOrders(
                        sessionManager.getUserId());
        if (orderAdapter == null) {

            orderAdapter = new OrderAdapter(orderList);
            recyclerOrders.setAdapter(orderAdapter);

        } else {

            orderAdapter = new OrderAdapter(orderList);
            recyclerOrders.setAdapter(orderAdapter);
        }
    }
}
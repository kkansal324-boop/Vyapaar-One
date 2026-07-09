package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditOrderActivity extends AppCompatActivity {

    Spinner spinnerCustomer, spinnerProduct;
    EditText edtQuantity;

    Button btnAddItem, btnUpdateOrder;
    ImageButton btnBack;

    TextView txtTotal;

    RecyclerView recyclerItems;

    DatabaseHelper db;

    ArrayList<Customer> customerList;
    ArrayList<Product> productList;
    ArrayList<OrderItem> itemList;

    OrderItemAdapter adapter;

    int orderId;
    private SessionManager sessionManager;
    double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);
        sessionManager = new SessionManager(this);
        spinnerCustomer = findViewById(R.id.spinnerCustomer);
        spinnerProduct = findViewById(R.id.spinnerProduct);
        edtQuantity = findViewById(R.id.edtQuantity);

        btnAddItem = findViewById(R.id.btnAddItem);
        btnUpdateOrder = findViewById(R.id.btnUpdateOrder);
        btnBack = findViewById(R.id.btnBack);

        txtTotal = findViewById(R.id.txtTotal);

        recyclerItems = findViewById(R.id.recyclerItems);
        recyclerItems.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);

        orderId = getIntent().getIntExtra("order_id", -1);

        if (orderId == -1) {
            Toast.makeText(this, "Invalid Order", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadCustomers();
        loadProducts();
        loadOrder();

        btnBack.setOnClickListener(v -> finish());
        btnAddItem.setOnClickListener(v -> {

            if (edtQuantity.getText().toString().trim().isEmpty()) {

                Toast.makeText(this,
                        "Enter Quantity",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            Product product =
                    productList.get(spinnerProduct.getSelectedItemPosition());

            double qty =
                    Double.parseDouble(edtQuantity.getText().toString());
            if (qty <= 0) {

                Toast.makeText(this,
                        "Quantity must be greater than 0",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            if (qty > product.getQuantity()) {

                Toast.makeText(this,
                        "Only " + product.getQuantity() + " " + product.getUnit() + " available",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            boolean found = false;

            for (OrderItem item : itemList) {

                if (item.getProductId() == product.getId()) {

                    item.setQuantity(qty);

                    item.setPrice(qty * product.getPricePerUnit());

                    found = true;

                    break;
                }
            }

            if (!found) {

                OrderItem item = new OrderItem();

                item.setOrderId(orderId);
                item.setProductId(product.getId());
                item.setProductName(product.getProductName());
                item.setQuantity(qty);
                item.setPrice(qty * product.getPricePerUnit());

                itemList.add(item);
            }

            double total = 0;

            for (OrderItem item : itemList) {

                total += item.getPrice();
            }

            txtTotal.setText("Total : ₹ " + total);

            adapter.notifyDataSetChanged();

            edtQuantity.setText("");

            Toast.makeText(this,
                    "Item Updated",
                    Toast.LENGTH_SHORT).show();

        });

        btnUpdateOrder.setOnClickListener(v -> {

            if (itemList.isEmpty()) {
                Toast.makeText(this, "No Items", Toast.LENGTH_SHORT).show();
                return;
            }

            new android.app.AlertDialog.Builder(this)
                    .setTitle("Update Order")
                    .setMessage("Save all changes to this order?")
                    .setPositiveButton("Update", (dialog, which) -> {

                        Customer customer =
                                customerList.get(spinnerCustomer.getSelectedItemPosition());

                        Order order = new Order();

                        order.setOrderId(orderId);
                        order.setCustomerId(customer.getCustomerId());
                        order.setCustomerName(customer.getCustomerName());
                        order.setOrderDate(db.getOrderById(orderId).getOrderDate());
                        order.setOrderStatus("CONFIRMED");

                        double total = 0;

                        for (OrderItem item : itemList) {

                            total += item.getPrice();
                        }

                        order.setTotalAmount(total);

                        boolean success = true;

                        success &= db.restoreOrderStock(orderId);

                        success &= db.deleteOrderItems(orderId);

                        success &= db.updateOrder(order);

                        for (OrderItem item : itemList) {

                            item.setOrderId(orderId);

                            success &= db.insertOrderItem(
                                    item,
                                    sessionManager.getUserId());
                        }

                        success &= db.deductNewOrderStock(itemList);

                        if (success) {

                            Toast.makeText(EditOrderActivity.this,
                                    "Order Updated Successfully",
                                    Toast.LENGTH_SHORT).show();

                            finish();

                        } else {

                            Toast.makeText(EditOrderActivity.this,
                                    "Order Update Failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        });
    }

    private void loadCustomers(){

        customerList = db.getAllCustomers();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item);

        for(Customer c : customerList){
            adapter.add(c.getCustomerName());
        }

        spinnerCustomer.setAdapter(adapter);
    }

    private void loadProducts(){

        productList = db.getAllProducts(sessionManager.getUserId());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item);

        for(Product p : productList){
            adapter.add(p.getProductName());
        }

        spinnerProduct.setAdapter(adapter);
    }

    private void loadOrder(){

        Order order = db.getOrderById(orderId);

        if(order==null){
            Toast.makeText(this,"Order not found",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        for (int i = 0; i < customerList.size(); i++) {

            if (customerList.get(i).getCustomerId() == order.getCustomerId()) {

                spinnerCustomer.setSelection(i);
                break;
            }
        }

        txtTotal.setText("Total : ₹ " + order.getTotalAmount());

        itemList = db.getOrderItemsByOrderId(orderId);

        adapter = new OrderItemAdapter(itemList);

        recyclerItems.setAdapter(adapter);

        adapter.setOnItemClickListener(new OrderItemAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {

                OrderItem item = itemList.get(position);

                edtQuantity.setText(String.valueOf(item.getQuantity()));

                for (int i = 0; i < productList.size(); i++) {

                    if (productList.get(i).getId() == item.getProductId()) {

                        spinnerProduct.setSelection(i);
                        break;
                    }
                }

                Toast.makeText(EditOrderActivity.this,
                        "Edit Item",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {

                itemList.remove(position);

                adapter.notifyDataSetChanged();

                calculateTotal();

                Toast.makeText(EditOrderActivity.this,
                        "Item Removed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void calculateTotal() {

        double total = 0;

        for (OrderItem item : itemList) {

            total += item.getPrice();
        }

        txtTotal.setText("Total : ₹ " + total);
    }
}
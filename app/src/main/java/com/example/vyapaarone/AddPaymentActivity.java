package com.example.vyapaarone;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddPaymentActivity extends AppCompatActivity {

    TextView txtOrderInfo, txtCustomerName, txtOrderTotal, txtPendingAmount;

    EditText edtPaidAmount;

    Spinner spinnerPaymentMethod;

    Button btnSavePayment;

    DatabaseHelper db;

    Order selectedOrder;
    Customer selectedCustomer;

    double paidAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        txtOrderInfo = findViewById(R.id.txtOrderInfo);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtOrderTotal = findViewById(R.id.txtOrderTotal);
        txtPendingAmount = findViewById(R.id.txtPendingAmount);

        edtPaidAmount = findViewById(R.id.edtPaidAmount);
        spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod);

        btnSavePayment = findViewById(R.id.btnSavePayment);

        db = new DatabaseHelper(this);

        int orderId = getIntent().getIntExtra("order_id", -1);

        if (orderId == -1) {
            Toast.makeText(this, "Invalid Order", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        selectedOrder = db.getOrderById(orderId);

        if (selectedOrder == null) {
            Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        selectedCustomer = db.getCustomerById(selectedOrder.getCustomerId());

        txtOrderInfo.setText("Order ID: " + selectedOrder.getOrderId());
        txtCustomerName.setText("Customer: " + selectedCustomer.getCustomerName());
        txtOrderTotal.setText("Total: ₹ " + selectedOrder.getTotalAmount());

        loadPaymentMethods();

        edtPaidAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculatePending();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnSavePayment.setOnClickListener(v -> savePayment());
    }

    private void loadPaymentMethods() {

        ArrayList<String> methods = new ArrayList<>();
        methods.add("Cash");
        methods.add("UPI");
        methods.add("Card");
        methods.add("Bank Transfer");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                methods
        );

        spinnerPaymentMethod.setAdapter(adapter);
    }

    private void calculatePending() {

        if (selectedOrder == null) return;

        double paid = 0;

        if (!edtPaidAmount.getText().toString().isEmpty()) {
            paid = Double.parseDouble(edtPaidAmount.getText().toString());
        }

        double alreadyPaid = db.getTotalPaidAmount(selectedOrder.getOrderId());

        double pending = selectedOrder.getTotalAmount() - (alreadyPaid + paid);

        if (pending < 0) {
            pending = 0;
        }

        txtPendingAmount.setText("Pending: ₹ " + pending);
    }

    private void savePayment() {

        if (edtPaidAmount.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Paid Amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double paid = Double.parseDouble(edtPaidAmount.getText().toString());

        double alreadyPaid = db.getTotalPaidAmount(selectedOrder.getOrderId());

        double pending = selectedOrder.getTotalAmount() - (alreadyPaid + paid);

        if (pending < 0) {
            pending = 0;
        }

        if (paid <= 0) {
            Toast.makeText(this, "Enter valid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        if (alreadyPaid + paid > selectedOrder.getTotalAmount()) {
            Toast.makeText(this, "Payment exceeds remaining balance", Toast.LENGTH_SHORT).show();
            return;
        }
        Payment payment = new Payment();
        payment.setOrderId(selectedOrder.getOrderId());
        payment.setCustomerId(selectedCustomer.getCustomerId());
        payment.setPaidAmount(paid);
        payment.setPendingAmount(pending);

        payment.setPaymentDate(
                new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        .format(new Date())
        );

        payment.setPaymentMethod(
                spinnerPaymentMethod.getSelectedItem().toString()
        );

        if (db.insertPayment(payment)) {
            Toast.makeText(this, "Payment Saved", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
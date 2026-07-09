package com.example.vyapaarone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;

public class InvoiceActivity extends AppCompatActivity {

    private TextView txtInvoiceNo;
    private TextView txtDate;
    private TextView txtCustomer;
    private TextView txtOrderId;
    private TextView txtStatus;
    private TextView txtSubtotal;
    private TextView txtGST;
    private TextView txtGrandTotal;
    private Button btnSharePdf, btnPrint, btnCancelInvoice;
    private LinearLayout layoutItems;
    private Spinner spinnerGST;

    private DatabaseHelper db;
    private int orderId;
    private Invoice invoice;
    private File pdfFile;
    private TextView txtBusinessName;
    private TextView txtOwner;
    private TextView txtBusinessGST;
    private TextView txtBusinessPhone;
    private TextView txtBusinessEmail;
    private TextView txtBusinessAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        db = new DatabaseHelper(this);

        txtBusinessName = findViewById(R.id.txtBusinessName);
        txtOwner = findViewById(R.id.txtOwner);
        txtBusinessGST = findViewById(R.id.txtBusinessGST);
        txtBusinessPhone = findViewById(R.id.txtBusinessPhone);
        txtBusinessEmail = findViewById(R.id.txtBusinessEmail);
        txtBusinessAddress = findViewById(R.id.txtBusinessAddress);
        txtInvoiceNo = findViewById(R.id.txtInvoiceNo);
        txtDate = findViewById(R.id.txtDate);
        txtCustomer = findViewById(R.id.txtCustomer);
        txtOrderId = findViewById(R.id.txtOrderId);
        txtStatus = findViewById(R.id.txtStatus);
        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtGST = findViewById(R.id.txtGST);
        txtGrandTotal = findViewById(R.id.txtGrandTotal);

        layoutItems = findViewById(R.id.layoutItems);
        spinnerGST = findViewById(R.id.spinnerGST);

        String[] gstRates = {
                "0",
                "5",
                "12",
                "18",
                "28"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        gstRates);

        spinnerGST.setAdapter(adapter);
        btnSharePdf = findViewById(R.id.btnSharePdf);
        btnPrint = findViewById(R.id.btnPrint);
        btnCancelInvoice = findViewById(R.id.btnCancelInvoice);

        orderId = getIntent().getIntExtra("order_id", -1);

        if (orderId == -1) {
            Toast.makeText(this, "Invalid Order", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadInvoice();
        spinnerGST.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(android.widget.AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id) {

                        calculateGST();
                    }

                    @Override
                    public void onNothingSelected(android.widget.AdapterView<?> parent) {

                    }
                });

        btnSharePdf.setOnClickListener(v -> {

            pdfFile = createInvoicePdf();

            Uri uri = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".provider",
                    pdfFile
            );

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/pdf");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(Intent.createChooser(intent, "Share Invoice"));
        });

        btnPrint.setOnClickListener(v -> {

            pdfFile = createInvoicePdf();

            printInvoice();

        });
        btnCancelInvoice.setOnClickListener(v -> {

            if (invoice == null) {
                return;
            }

            if ("CANCELLED".equalsIgnoreCase(invoice.getStatus())) {

                Toast.makeText(
                        InvoiceActivity.this,
                        "Invoice already cancelled",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            new androidx.appcompat.app.AlertDialog.Builder(InvoiceActivity.this)
                    .setTitle("Cancel Invoice")
                    .setMessage("Are you sure you want to cancel this invoice?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        if (db.cancelInvoice(invoice.getInvoiceId())) {

                            Toast.makeText(
                                    InvoiceActivity.this,
                                    "Invoice Cancelled Successfully",
                                    Toast.LENGTH_SHORT
                            ).show();

                            loadInvoice();

                        } else {

                            Toast.makeText(
                                    InvoiceActivity.this,
                                    "Failed to cancel invoice",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        });
    }

    private void loadInvoice() {

        if (!db.invoiceExists(orderId)) {

            Order order = db.getOrderById(orderId);

            if (order == null) {
                Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show();
                return;
            }

            invoice = new Invoice();
            invoice.setInvoiceNumber(db.generateNextInvoiceNumber());
            invoice.setOrderId(order.getOrderId());
            invoice.setCustomerId(order.getCustomerId());
            invoice.setCustomerName(order.getCustomerName());

            invoice.setInvoiceDate(
                    new SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault())
                            .format(new Date())
            );

            double subTotal = order.getTotalAmount();

// 18% GST
            double gstPercent = 18;

            double gstAmount = (subTotal * gstPercent) / 100;

            double grandTotal = subTotal + gstAmount;

            invoice.setSubTotal(subTotal);
            invoice.setGstPercent(gstPercent);
            invoice.setGstAmount(gstAmount);
            invoice.setGrandTotal(grandTotal);
            invoice.setStatus("ACTIVE");
            db.insertInvoice(invoice);
        }

        invoice = db.getInvoiceByOrderId(orderId);
        if (invoice != null) {

            if ("CANCELLED".equalsIgnoreCase(invoice.getStatus())) {

                btnCancelInvoice.setEnabled(false);
                btnCancelInvoice.setText("Invoice Cancelled");

                spinnerGST.setEnabled(false);

                btnSharePdf.setEnabled(false);
                btnPrint.setEnabled(false);

                btnSharePdf.setAlpha(0.5f);
                btnPrint.setAlpha(0.5f);
                btnCancelInvoice.setAlpha(0.5f);

            } else {

                btnCancelInvoice.setEnabled(true);
                btnCancelInvoice.setText("Cancel Invoice");

                spinnerGST.setEnabled(true);

                btnSharePdf.setEnabled(true);
                btnPrint.setEnabled(true);

                btnSharePdf.setAlpha(1f);
                btnPrint.setAlpha(1f);
                btnCancelInvoice.setAlpha(1f);
            }
        }

        if (invoice == null) {
            Toast.makeText(this, "Invoice not found", Toast.LENGTH_SHORT).show();
            return;
        }
        // Select saved GST in Spinner
        String gst = String.valueOf((int) invoice.getGstPercent());

        ArrayAdapter adapter = (ArrayAdapter) spinnerGST.getAdapter();

        int position = adapter.getPosition(gst);

        if (position >= 0) {
            spinnerGST.setSelection(position);
        }
        BusinessProfile profile = db.getBusinessProfile();

        if (profile != null) {

            txtBusinessName.setText(profile.getBusinessName());

            txtOwner.setText("Owner : " + profile.getOwnerName());

            if (profile.getGstNumber().trim().isEmpty()) {
                txtBusinessGST.setVisibility(View.GONE);
            } else {
                txtBusinessGST.setVisibility(View.VISIBLE);
                txtBusinessGST.setText("GSTIN : " + profile.getGstNumber());
            }

            if (profile.getPhone().trim().isEmpty()) {
                txtBusinessPhone.setVisibility(View.GONE);
            } else {
                txtBusinessPhone.setVisibility(View.VISIBLE);
                txtBusinessPhone.setText("Phone : " + profile.getPhone());
            }

            if (profile.getEmail().trim().isEmpty()) {
                txtBusinessEmail.setVisibility(View.GONE);
            } else {
                txtBusinessEmail.setVisibility(View.VISIBLE);
                txtBusinessEmail.setText("Email : " + profile.getEmail());
            }

            if (profile.getAddress().trim().isEmpty()) {
                txtBusinessAddress.setVisibility(View.GONE);
            } else {
                txtBusinessAddress.setVisibility(View.VISIBLE);
                txtBusinessAddress.setText(profile.getAddress());
            }
        }

        txtInvoiceNo.setText("Invoice No : " + invoice.getInvoiceNumber());
        txtDate.setText("Date : " + invoice.getInvoiceDate());
        txtCustomer.setText("Customer : " + invoice.getCustomerName());
        txtOrderId.setText("Order ID : " + invoice.getOrderId());
        txtStatus.setText("Status : " + invoice.getStatus());

        if ("CANCELLED".equalsIgnoreCase(invoice.getStatus())) {

            txtStatus.setTextColor(
                    getResources().getColor(android.R.color.holo_red_dark));

        } else {

            txtStatus.setTextColor(
                    getResources().getColor(android.R.color.holo_green_dark));
        }
        txtSubtotal.setText(
                "Subtotal : ₹ " +
                        String.format(Locale.getDefault(),"%.2f",
                                invoice.getSubTotal())
        );
        txtGST.setText(
                "GST (" +
                        invoice.getGstPercent() +
                        "%) : ₹ " +
                        String.format(Locale.getDefault(),"%.2f",
                                invoice.getGstAmount())
        );
        txtGrandTotal.setText(
                "Grand Total : ₹ " +
                        String.format(Locale.getDefault(),"%.2f",
                                invoice.getGrandTotal())
        );

        layoutItems.removeAllViews();

        ArrayList<OrderItem> items = db.getOrderItemsByOrderId(orderId);

        LayoutInflater inflater = LayoutInflater.from(this);

        for (OrderItem item : items) {

            View view = inflater.inflate(
                    R.layout.invoice_item,
                    layoutItems,
                    false
            );

            TextView txtProduct = view.findViewById(R.id.txtProduct);
            TextView txtQty = view.findViewById(R.id.txtQty);
            TextView txtRate = view.findViewById(R.id.txtRate);
            TextView txtAmount = view.findViewById(R.id.txtAmount);

            // Get product details to read its unit
            Product product = db.getProductById(item.getProductId());

            String unit = "";

            if (product != null) {
                unit = product.getUnit();
            }

            double rate = item.getPrice();
            double amount = rate * item.getQuantity();

            txtProduct.setText(item.getProductName());

// Quantity + Unit
            if (item.getQuantity() == (int) item.getQuantity()) {
                txtQty.setText((int) item.getQuantity() + " " + unit);
            } else {
                txtQty.setText(item.getQuantity() + " " + unit);
            }

            txtRate.setText(String.format(Locale.getDefault(), "₹ %.2f", rate));
            txtAmount.setText(String.format(Locale.getDefault(), "₹ %.2f", amount));

            layoutItems.addView(view);
        }
    }
    private void calculateGST() {

        Invoice invoice = db.getInvoiceByOrderId(orderId);

        if (invoice == null)
            return;

        double subtotal = invoice.getSubTotal();

        double gstPercent = Double.parseDouble(
                spinnerGST.getSelectedItem().toString());

        double gstAmount = subtotal * gstPercent / 100.0;

        double grandTotal = subtotal + gstAmount;

        // Update UI
        txtSubtotal.setText(String.format(
                Locale.getDefault(),
                "Subtotal : ₹ %.2f",
                subtotal));

        txtGST.setText(String.format(
                Locale.getDefault(),
                "GST (%.0f%%) : ₹ %.2f",
                gstPercent,
                gstAmount));

        txtGrandTotal.setText(String.format(
                Locale.getDefault(),
                "Grand Total : ₹ %.2f",
                grandTotal));

        // Update Invoice
        invoice.setGstPercent(gstPercent);
        invoice.setGstAmount(gstAmount);
        invoice.setGrandTotal(grandTotal);

        db.updateInvoice(invoice);

        // IMPORTANT
        Order order = db.getOrderById(orderId);

        if (order != null) {

            order.setTotalAmount(grandTotal);

            db.updateOrder(order);
        }

        this.invoice = invoice;
    }
    private File createInvoicePdf() {



        // Get Business Profile
        BusinessProfile profile = db.getBusinessProfile();

        PdfDocument pdfDocument = new PdfDocument();

        Paint titlePaint = new Paint();
        titlePaint.setTextSize(24);
        titlePaint.setFakeBoldText(true);

        Paint textPaint = new Paint();
        textPaint.setTextSize(14);

        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        int y = 40;

        // ===========================
        // APP TITLE
        // ===========================

        page.getCanvas().drawText("Vyapaar One", 180, y, titlePaint);

        y += 30;
        page.getCanvas().drawText("Business Management System", 150, y, textPaint);

        // ===========================
        // BUSINESS PROFILE
        // ===========================

        if (profile != null) {

            y += 40;

            page.getCanvas().drawText(
                    profile.getBusinessName(),
                    40,
                    y,
                    titlePaint
            );

            if (!profile.getOwnerName().trim().isEmpty()) {
                y += 22;
                page.getCanvas().drawText(
                        "Owner : " + profile.getOwnerName(),
                        40,
                        y,
                        textPaint
                );
            }

            if (!profile.getGstNumber().trim().isEmpty()) {
                y += 22;
                page.getCanvas().drawText(
                        "GSTIN : " + profile.getGstNumber(),
                        40,
                        y,
                        textPaint
                );
            }

            if (!profile.getPhone().trim().isEmpty()) {
                y += 22;
                page.getCanvas().drawText(
                        "Phone : " + profile.getPhone(),
                        40,
                        y,
                        textPaint
                );
            }

            if (!profile.getEmail().trim().isEmpty()) {
                y += 22;
                page.getCanvas().drawText(
                        "Email : " + profile.getEmail(),
                        40,
                        y,
                        textPaint
                );
            }

            if (!profile.getAddress().trim().isEmpty()) {
                y += 22;
                page.getCanvas().drawText(
                        "Address : " + profile.getAddress(),
                        40,
                        y,
                        textPaint
                );
            }
        }

        // ===========================
        // LINE
        // ===========================

        y += 35;

        page.getCanvas().drawText(
                "--------------------------------------------------------------",
                40,
                y,
                textPaint
        );

        // ===========================
        // INVOICE DETAILS
        // ===========================

        y += 25;
        page.getCanvas().drawText(txtInvoiceNo.getText().toString(), 40, y, textPaint);

        y += 25;
        page.getCanvas().drawText(txtDate.getText().toString(), 40, y, textPaint);

        y += 25;
        page.getCanvas().drawText(txtCustomer.getText().toString(), 40, y, textPaint);

        y += 25;
        page.getCanvas().drawText(txtOrderId.getText().toString(), 40, y, textPaint);

        y += 35;

        page.getCanvas().drawText(
                "--------------------------------------------------------------",
                40,
                y,
                textPaint
        );

        // ===========================
        // TABLE HEADER
        // ===========================

        y += 25;

        page.getCanvas().drawText("Product", 40, y, titlePaint);
        page.getCanvas().drawText("Qty", 220, y, titlePaint);
        page.getCanvas().drawText("Rate", 320, y, titlePaint);
        page.getCanvas().drawText("Amount", 430, y, titlePaint);

        y += 25;

        // ===========================
        // ITEMS
        // ===========================

        ArrayList<OrderItem> items = db.getOrderItemsByOrderId(orderId);

        for (OrderItem item : items) {

            Product product = db.getProductById(item.getProductId());

            String unit = "";

            if (product != null)
                unit = product.getUnit();

            double amount = item.getPrice() * item.getQuantity();

            page.getCanvas().drawText(
                    item.getProductName(),
                    40,
                    y,
                    textPaint
            );

            String qty;

            if (item.getQuantity() == (int) item.getQuantity()) {
                qty = (int) item.getQuantity() + " " + unit;
            } else {
                qty = item.getQuantity() + " " + unit;
            }

            page.getCanvas().drawText(
                    qty,
                    220,
                    y,
                    textPaint
            );

            page.getCanvas().drawText(
                    String.format(Locale.getDefault(),
                            "₹ %.2f",
                            item.getPrice()),
                    320,
                    y,
                    textPaint
            );

            page.getCanvas().drawText(
                    String.format(Locale.getDefault(),
                            "₹ %.2f",
                            amount),
                    430,
                    y,
                    textPaint
            );

            y += 22;
        }

        // ===========================
        // TOTALS
        // ===========================

        y += 30;

        page.getCanvas().drawText(
                String.format(
                        Locale.getDefault(),
                        "Subtotal : ₹ %.2f",
                        invoice.getSubTotal()
                ),
                40,
                y,
                textPaint
        );

        y += 25;

        page.getCanvas().drawText(
                String.format(
                        Locale.getDefault(),
                        "GST (%.0f%%) : ₹ %.2f",
                        invoice.getGstPercent(),
                        invoice.getGstAmount()
                ),
                40,
                y,
                textPaint
        );

        y += 25;

        page.getCanvas().drawText(
                String.format(
                        Locale.getDefault(),
                        "Grand Total : ₹ %.2f",
                        invoice.getGrandTotal()
                ),
                40,
                y,
                titlePaint
        );

        pdfDocument.finishPage(page);

        File folder = new File(
                getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "Invoices"
        );

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(
                folder,
                invoice.getInvoiceNumber() + ".pdf"
        );

        try {

            FileOutputStream outputStream = new FileOutputStream(file);

            pdfDocument.writeTo(outputStream);

            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfDocument.close();

        return file;
    }
    private void printInvoice() {

        if (pdfFile == null || !pdfFile.exists()) {

            Toast.makeText(
                    this,
                    "Please generate PDF first.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        PrintManager printManager =
                (PrintManager) getSystemService(PRINT_SERVICE);

        PrintDocumentAdapter adapter =
                new PdfDocumentAdapter(
                        this,
                        pdfFile.getAbsolutePath());

        printManager.print(
                "Vyapaar One Invoice",
                adapter,
                new PrintAttributes.Builder().build());
    }
}
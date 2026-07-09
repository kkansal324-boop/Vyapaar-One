package com.example.vyapaarone;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BillHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerBills;
    private TextView txtEmpty;
    private EditText etSearch;
    private Spinner spinnerFilter;

    private DatabaseHelper db;

    private ArrayList<Invoice> invoiceList;
    private TextView txtTotalBills;
    private TextView txtActiveBills;
    private TextView txtCancelledBills;
    private TextView txtRevenue;
    private BillHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_history);

        recyclerBills = findViewById(R.id.recyclerBills);
        txtEmpty = findViewById(R.id.txtEmpty);
        txtTotalBills = findViewById(R.id.txtTotalBills);
        txtActiveBills = findViewById(R.id.txtActiveBills);
        txtCancelledBills = findViewById(R.id.txtCancelledBills);
        txtRevenue = findViewById(R.id.txtRevenue);
        etSearch = findViewById(R.id.etSearch);
        spinnerFilter = findViewById(R.id.spinnerFilter);

        db = new DatabaseHelper(this);

        recyclerBills.setLayoutManager(new LinearLayoutManager(this));

        invoiceList = db.getAllInvoices();

        adapter = new BillHistoryAdapter(this, invoiceList);
        recyclerBills.setAdapter(adapter);

        checkEmpty();
        updateStatistics();

        String[] filterItems = {
                "Newest",
                "Oldest",
                "Highest Amount",
                "Lowest Amount",
                "ACTIVE",
                "CANCELLED"
        };

        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        filterItems);

        spinnerFilter.setAdapter(spinnerAdapter);

        spinnerFilter.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(android.widget.AdapterView<?> parent,
                                               android.view.View view,
                                               int position,
                                               long id) {

                        filterInvoices();
                    }

                    @Override
                    public void onNothingSelected(android.widget.AdapterView<?> parent) {

                    }
                });

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start,
                                          int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start,
                                      int before,
                                      int count) {

                filterInvoices();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filterInvoices() {

        String search =
                etSearch.getText().toString().trim().toLowerCase();

        String filter =
                spinnerFilter.getSelectedItem().toString();

        ArrayList<Invoice> filteredList = db.getAllInvoices();

        // Search
        if (!search.isEmpty()) {

            ArrayList<Invoice> temp = new ArrayList<>();

            for (Invoice invoice : filteredList) {

                if (invoice.getInvoiceNumber().toLowerCase().contains(search)
                        ||
                        invoice.getCustomerName().toLowerCase().contains(search)) {

                    temp.add(invoice);
                }
            }

            filteredList = temp;
        }

        // Status Filter

        if (filter.equals("ACTIVE") || filter.equals("CANCELLED")) {

            ArrayList<Invoice> temp = new ArrayList<>();

            for (Invoice invoice : filteredList) {

                if (invoice.getStatus().equalsIgnoreCase(filter)) {

                    temp.add(invoice);

                }
            }

            filteredList = temp;
        }

        // Sorting

        if (filter.equals("Oldest")) {

            java.util.Collections.reverse(filteredList);

        } else if (filter.equals("Highest Amount")) {

            filteredList.sort((a, b) ->
                    Double.compare(b.getGrandTotal(), a.getGrandTotal()));

        } else if (filter.equals("Lowest Amount")) {

            filteredList.sort((a, b) ->
                    Double.compare(a.getGrandTotal(), b.getGrandTotal()));
        }

        adapter.updateList(filteredList);

        checkEmpty();
    }
    private void checkEmpty() {

        if (adapter.getItemCount() == 0) {

            txtEmpty.setVisibility(TextView.VISIBLE);

        } else {

            txtEmpty.setVisibility(TextView.GONE);

        }
    }
    private void updateStatistics() {

        int total = invoiceList.size();

        int active = 0;
        int cancelled = 0;

        double revenue = 0;

        for (Invoice invoice : invoiceList) {

            if ("ACTIVE".equalsIgnoreCase(invoice.getStatus())) {

                active++;
                revenue += invoice.getGrandTotal();

            } else {

                cancelled++;

            }

        }

        txtTotalBills.setText("Total Bills : " + total);

        txtActiveBills.setText("Active Bills : " + active);

        txtCancelledBills.setText("Cancelled Bills : " + cancelled);

        txtRevenue.setText(String.format(
                java.util.Locale.getDefault(),
                "Revenue : ₹ %.2f",
                revenue
        ));
    }
}
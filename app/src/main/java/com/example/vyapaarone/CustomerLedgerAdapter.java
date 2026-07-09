package com.example.vyapaarone;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerLedgerAdapter extends RecyclerView.Adapter<CustomerLedgerAdapter.ViewHolder> {

    private final ArrayList<CustomerLedger> list;

    public CustomerLedgerAdapter(ArrayList<CustomerLedger> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCustomerName;
        TextView txtPhone;
        TextView txtSales;
        TextView txtPaid;
        TextView txtPending;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtSales = itemView.findViewById(R.id.txtTotalSales);
            txtPaid = itemView.findViewById(R.id.txtTotalPaid);
            txtPending = itemView.findViewById(R.id.txtPending);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer_ledger, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CustomerLedger ledger = list.get(position);

        holder.txtCustomerName.setText(ledger.getCustomerName());
        holder.txtPhone.setText(ledger.getCustomerPhone());

        holder.txtSales.setText("Sales : ₹ " + ledger.getTotalSales());
        holder.txtPaid.setText("Paid : ₹ " + ledger.getTotalPaid());
        holder.txtPending.setText("Pending : ₹ " + ledger.getPendingAmount());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    v.getContext(),
                    CustomerLedgerDetailsActivity.class);

            intent.putExtra(
                    "customer_id",
                    ledger.getCustomerId());

            v.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
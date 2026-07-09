package com.example.vyapaarone;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerTransactionAdapter extends RecyclerView.Adapter<CustomerTransactionAdapter.ViewHolder> {

    private final ArrayList<CustomerTransaction> list;

    public CustomerTransactionAdapter(ArrayList<CustomerTransaction> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTransactionTitle;
        TextView txtTransactionDate;
        TextView txtTransactionAmount;
        TextView txtRunningBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTransactionTitle = itemView.findViewById(R.id.txtTransactionTitle);
            txtTransactionDate = itemView.findViewById(R.id.txtTransactionDate);
            txtTransactionAmount = itemView.findViewById(R.id.txtTransactionAmount);
            txtRunningBalance = itemView.findViewById(R.id.txtRunningBalance);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer_transaction, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CustomerTransaction transaction = list.get(position);

        holder.txtTransactionTitle.setText(transaction.getTitle());
        holder.txtTransactionDate.setText(transaction.getDate());

        if (transaction.getTransactionType().equals("INVOICE")) {

            holder.txtTransactionAmount.setText("+ ₹ " + transaction.getAmount());
            holder.txtTransactionAmount.setTextColor(Color.parseColor("#2E7D32"));

        } else {

            holder.txtTransactionAmount.setText("- ₹ " + transaction.getAmount());
            holder.txtTransactionAmount.setTextColor(Color.parseColor("#D32F2F"));
        }

        holder.txtRunningBalance.setText(
                "Balance : ₹ " + transaction.getRunningBalance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
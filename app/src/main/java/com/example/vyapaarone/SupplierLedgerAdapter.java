package com.example.vyapaarone;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupplierLedgerAdapter extends RecyclerView.Adapter<SupplierLedgerAdapter.ViewHolder> {

    Context context;
    ArrayList<SupplierLedger> ledgerList;

    public SupplierLedgerAdapter(Context context,
                                 ArrayList<SupplierLedger> ledgerList) {

        this.context = context;
        this.ledgerList = ledgerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_supplier_ledger,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        SupplierLedger ledger = ledgerList.get(position);

        holder.txtSupplierName.setText(ledger.getSupplierName());

        holder.txtOpeningBalance.setText(
                "Opening : ₹ " +
                        String.format("%.2f",
                                ledger.getOpeningBalance()));

        holder.txtPurchase.setText(
                "Purchase : ₹ " +
                        String.format("%.2f",
                                ledger.getTotalPurchase()));

        holder.txtPaid.setText(
                "Paid : ₹ " +
                        String.format("%.2f",
                                ledger.getTotalPaid()));

        holder.txtRemaining.setText(
                "Balance : ₹ " +
                        String.format("%.2f",
                                ledger.getRemainingBalance()));

        holder.txtRemaining.setTextColor(
                Color.parseColor("#D32F2F"));

    }

    @Override
    public int getItemCount() {
        return ledgerList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardLedger;

        TextView txtSupplierName;
        TextView txtOpeningBalance;
        TextView txtPurchase;
        TextView txtPaid;
        TextView txtRemaining;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardLedger = itemView.findViewById(R.id.cardLedger);

            txtSupplierName = itemView.findViewById(R.id.txtSupplierName);
            txtOpeningBalance = itemView.findViewById(R.id.txtOpeningBalance);
            txtPurchase = itemView.findViewById(R.id.txtPurchase);
            txtPaid = itemView.findViewById(R.id.txtPaid);
            txtRemaining = itemView.findViewById(R.id.txtRemaining);
        }
    }
}
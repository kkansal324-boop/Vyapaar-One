package com.example.vyapaarone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {

    private ArrayList<Supplier> supplierList;
    private OnSupplierClickListener listener;

    public interface OnSupplierClickListener {
        void onSupplierClick(Supplier supplier);
    }

    public SupplierAdapter(ArrayList<Supplier> supplierList,
                           OnSupplierClickListener listener) {

        this.supplierList = supplierList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_supplier, parent, false);

        return new SupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {

        Supplier supplier = supplierList.get(position);

        holder.txtSupplierName.setText(supplier.getSupplierName());

        holder.txtCompany.setText(supplier.getCompanyName());

        holder.txtMobile.setText("Mobile : " + supplier.getMobile());

        holder.txtBalance.setText(
                "₹ " + String.format("%.2f", supplier.getOpeningBalance()));

        holder.txtBalanceType.setText(supplier.getBalanceType());

        if (supplier.getBalanceType().equalsIgnoreCase("Debit")) {

            holder.txtBalanceType.setBackgroundColor(
                    holder.itemView.getResources()
                            .getColor(android.R.color.holo_red_dark));

        } else {

            holder.txtBalanceType.setBackgroundColor(
                    holder.itemView.getResources()
                            .getColor(android.R.color.holo_green_dark));

        }

        holder.itemView.setOnClickListener(v -> {

            if (listener != null) {
                listener.onSupplierClick(supplier);
            }

        });

    }

    @Override
    public int getItemCount() {
        return supplierList.size();
    }

    static class SupplierViewHolder extends RecyclerView.ViewHolder {

        CardView cardSupplier;

        TextView txtSupplierName;
        TextView txtCompany;
        TextView txtMobile;
        TextView txtBalance;
        TextView txtBalanceType;

        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);

            cardSupplier = (CardView) itemView;

            txtSupplierName = itemView.findViewById(R.id.txtSupplierName);
            txtCompany = itemView.findViewById(R.id.txtCompany);
            txtMobile = itemView.findViewById(R.id.txtMobile);
            txtBalance = itemView.findViewById(R.id.txtBalance);
            txtBalanceType = itemView.findViewById(R.id.txtBalanceType);
        }
    }
}
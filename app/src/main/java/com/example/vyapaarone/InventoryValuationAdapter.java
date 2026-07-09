package com.example.vyapaarone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class InventoryValuationAdapter extends RecyclerView.Adapter<InventoryValuationAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<InventoryValuation> list;

    public InventoryValuationAdapter(Context context,
                                     ArrayList<InventoryValuation> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_inventory_valuation,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {

        InventoryValuation item = list.get(position);

        holder.txtProductName.setText(item.getProductName());

        holder.txtStock.setText(String.format(
                Locale.getDefault(),
                "Stock : %.2f",
                item.getStock()));

        holder.txtPurchasePrice.setText(String.format(
                Locale.getDefault(),
                "Purchase Price : ₹ %.2f",
                item.getPurchasePrice()));

        holder.txtInventoryValue.setText(String.format(
                Locale.getDefault(),
                "Inventory Value : ₹ %.2f",
                item.getInventoryValue()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName;
        TextView txtStock;
        TextView txtPurchasePrice;
        TextView txtInventoryValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtStock = itemView.findViewById(R.id.txtStock);
            txtPurchasePrice = itemView.findViewById(R.id.txtPurchasePrice);
            txtInventoryValue = itemView.findViewById(R.id.txtInventoryValue);
        }
    }
}
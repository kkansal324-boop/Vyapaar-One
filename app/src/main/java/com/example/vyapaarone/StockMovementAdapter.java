package com.example.vyapaarone;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StockMovementAdapter extends RecyclerView.Adapter<StockMovementAdapter.ViewHolder> {

    private final ArrayList<StockMovement> list;

    public StockMovementAdapter(ArrayList<StockMovement> list) {
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName, txtMovementType,
                txtQuantity, txtStockAfter, txtDate;

        ViewHolder(View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtMovementType = itemView.findViewById(R.id.txtMovementType);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtStockAfter = itemView.findViewById(R.id.txtStockAfter);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_movement_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StockMovement movement = list.get(position);

        holder.txtProductName.setText(movement.getProductName());

        holder.txtMovementType.setText("Type : " + movement.getMovementType());

        holder.txtQuantity.setText("Quantity : " + movement.getQuantity());

        holder.txtStockAfter.setText("Stock After : " + movement.getStockAfter());

        holder.txtDate.setText("Date : " + movement.getMovementDate());

        if (movement.getMovementType().equalsIgnoreCase("PURCHASE")) {

            holder.txtMovementType.setTextColor(Color.parseColor("#2E7D32"));

        } else if (movement.getMovementType().equalsIgnoreCase("SALE")) {

            holder.txtMovementType.setTextColor(Color.parseColor("#1565C0"));

        } else if (movement.getMovementType().equalsIgnoreCase("RETURN")) {

            holder.txtMovementType.setTextColor(Color.parseColor("#EF6C00"));

        } else {

            holder.txtMovementType.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
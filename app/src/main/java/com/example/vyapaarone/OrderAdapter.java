package com.example.vyapaarone;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    ArrayList<Order> list;

    public OrderAdapter(ArrayList<Order> list) {
        this.list = list;
    }

    // =========================
    // VIEW HOLDER
    // =========================
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCustomerName, txtDate, txtTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Order order = list.get(position);

        holder.txtCustomerName.setText(order.getCustomerName());
        holder.txtDate.setText(order.getOrderDate());
        holder.txtTotal.setText("₹ " + order.getTotalAmount());

        // Click → Open Order Details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), OrderDetailsActivity.class);
            intent.putExtra("order_id", order.getOrderId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
package com.example.vyapaarone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    ArrayList<OrderItem> list;

    OnItemClickListener listener;

    public interface OnItemClickListener {

        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;
    }

    public OrderItemAdapter(ArrayList<OrderItem> list) {

        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName, txtQuantity, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtPrice = itemView.findViewById(R.id.txtPrice);

            itemView.setOnClickListener(v -> {

                if (listener != null) {

                    listener.onItemClick(getAdapterPosition());
                }

            });

            itemView.setOnLongClickListener(v -> {

                if (listener != null) {

                    listener.onItemLongClick(getAdapterPosition());
                }

                return true;
            });

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_details_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderItem item = list.get(position);

        holder.txtProductName.setText(item.getProductName());
        holder.txtQuantity.setText("Qty : " + item.getQuantity());
        holder.txtPrice.setText("₹ " + item.getPrice());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
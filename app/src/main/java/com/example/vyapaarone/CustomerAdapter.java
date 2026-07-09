package com.example.vyapaarone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Customer> customerList;

    public CustomerAdapter(Context context, ArrayList<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.customer_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Customer customer = customerList.get(position);

        holder.txtCustomerName.setText(customer.getCustomerName());
        holder.txtCustomerPhone.setText("📞 " + customer.getCustomerPhone());
        holder.txtCustomerEmail.setText("📧 " + customer.getCustomerEmail());
        holder.txtCustomerAddress.setText("🏠 " + customer.getCustomerAddress());

        holder.cardCustomer.setOnClickListener(v -> {

            Intent intent = new Intent(context, CustomerDetailsActivity.class);

            intent.putExtra("customerId", customer.getCustomerId());

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardCustomer;

        TextView txtCustomerName;
        TextView txtCustomerPhone;
        TextView txtCustomerEmail;
        TextView txtCustomerAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardCustomer = itemView.findViewById(R.id.cardCustomer);

            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtCustomerPhone = itemView.findViewById(R.id.txtCustomerPhone);
            txtCustomerEmail = itemView.findViewById(R.id.txtCustomerEmail);
            txtCustomerAddress = itemView.findViewById(R.id.txtCustomerAddress);
        }
    }
}
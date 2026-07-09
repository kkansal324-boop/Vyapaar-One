package com.example.vyapaarone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class BillHistoryAdapter extends RecyclerView.Adapter<BillHistoryAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Invoice> invoiceList;

    public BillHistoryAdapter(Context context,
                              ArrayList<Invoice> invoiceList) {

        this.context = context;
        this.invoiceList = invoiceList;
    }

    public void updateList(ArrayList<Invoice> newList) {

        invoiceList.clear();

        invoiceList.addAll(newList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.bill_history_item,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {

        Invoice invoice = invoiceList.get(position);

        holder.txtInvoiceNumber.setText(invoice.getInvoiceNumber());

        holder.txtCustomerName.setText(
                "Customer : " + invoice.getCustomerName());

        holder.txtInvoiceDate.setText(
                "Date : " + invoice.getInvoiceDate());

        holder.txtGrandTotal.setText(
                String.format(
                        Locale.getDefault(),
                        "₹ %.2f",
                        invoice.getGrandTotal()
                )
        );

        // Invoice Status
        holder.txtStatus.setText(invoice.getStatus());
        holder.txtStatus.setTextColor(0xFFFFFFFF);

        if ("CANCELLED".equalsIgnoreCase(invoice.getStatus())) {

            holder.txtStatus.setBackgroundColor(0xFFD32F2F);

        } else {

            holder.txtStatus.setBackgroundColor(0xFF388E3C);

        }

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    InvoiceActivity.class);

            intent.putExtra(
                    "order_id",
                    invoice.getOrderId());

            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {

        return invoiceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtInvoiceNumber;
        TextView txtCustomerName;
        TextView txtInvoiceDate;
        TextView txtGrandTotal;
        TextView txtStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtInvoiceNumber =
                    itemView.findViewById(R.id.txtInvoiceNumber);

            txtCustomerName =
                    itemView.findViewById(R.id.txtCustomerName);

            txtInvoiceDate =
                    itemView.findViewById(R.id.txtInvoiceDate);

            txtGrandTotal =
                    itemView.findViewById(R.id.txtGrandTotal);

            txtStatus =
                    itemView.findViewById(R.id.txtStatus);
        }
    }
}
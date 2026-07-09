package com.example.vyapaarone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private Context context;
    private ArrayList<Purchase> purchaseList;
    private DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    public PurchaseAdapter(Context context,
                           ArrayList<Purchase> purchaseList) {

        this.context = context;
        this.purchaseList = purchaseList;
        databaseHelper = new DatabaseHelper(context);
        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_purchase, parent, false);

        return new PurchaseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(
            @NonNull PurchaseViewHolder holder,
            int position) {

        Purchase purchase = purchaseList.get(position);

        Supplier supplier =
                databaseHelper.getSupplierById(
                        purchase.getSupplierId(),
                        sessionManager.getUserId());

        Product product =
                databaseHelper.getProductById(
                        purchase.getProductId());

        if (supplier != null) {

            holder.txtSupplierName.setText(
                    supplier.getSupplierName());

        } else {

            holder.txtSupplierName.setText("Unknown Supplier");

        }

        if (product != null) {

            holder.txtProductName.setText(
                    product.getProductName());

        } else {

            holder.txtProductName.setText("Unknown Product");

        }

        holder.txtQuantity.setText(
                "Quantity : " + purchase.getQuantity());

        holder.txtPurchasePrice.setText(
                "Purchase Price : ₹ " +
                        purchase.getPurchasePrice());

        holder.txtTotalAmount.setText(
                "Total : ₹ " +
                        purchase.getTotalAmount());

        holder.txtPurchaseDate.setText(
                purchase.getPurchaseDate());

        holder.txtTotalAmount.setTextColor(
                Color.parseColor("#2E7D32"));
        // ===========================
        // EDIT PURCHASE
        // ===========================

        holder.btnEditPurchase.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    AddPurchaseActivity.class);

            intent.putExtra(
                    "purchase_id",
                    purchase.getPurchaseId());

            context.startActivity(intent);

        });

        // ===========================
        // DELETE PURCHASE
        // ===========================

        holder.btnDeletePurchase.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete Purchase")
                    .setMessage("Are you sure you want to delete this purchase?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        databaseHelper.deletePurchase(
                                purchase.getPurchaseId());

                        purchaseList.remove(position);

                        notifyItemRemoved(position);

                        notifyItemRangeChanged(
                                position,
                                purchaseList.size());

                        Toast.makeText(
                                context,
                                "Purchase Deleted Successfully",
                                Toast.LENGTH_SHORT
                        ).show();

                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        });

    }

    @Override
    public int getItemCount() {

        return purchaseList.size();

    }

    static class PurchaseViewHolder extends RecyclerView.ViewHolder {

        CardView cardPurchase;

        TextView txtSupplierName;
        TextView txtProductName;
        TextView txtQuantity;
        TextView txtPurchasePrice;
        TextView txtTotalAmount;
        TextView txtPurchaseDate;

        Button btnEditPurchase;
        Button btnDeletePurchase;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);

            cardPurchase = (CardView) itemView;

            txtSupplierName = itemView.findViewById(R.id.txtSupplierName);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtPurchasePrice = itemView.findViewById(R.id.txtPurchasePrice);
            txtTotalAmount = itemView.findViewById(R.id.txtTotalAmount);
            txtPurchaseDate = itemView.findViewById(R.id.txtPurchaseDate);

            btnEditPurchase = itemView.findViewById(R.id.btnEditPurchase);
            btnDeletePurchase = itemView.findViewById(R.id.btnDeletePurchase);

        }
    }
}
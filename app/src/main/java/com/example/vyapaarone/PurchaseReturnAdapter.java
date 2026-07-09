package com.example.vyapaarone;

import android.app.AlertDialog;
import android.content.Context;
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

public class PurchaseReturnAdapter extends RecyclerView.Adapter<PurchaseReturnAdapter.ReturnViewHolder> {

    private Context context;
    private ArrayList<PurchaseReturn> returnList;
    private DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    public PurchaseReturnAdapter(Context context,
                                 ArrayList<PurchaseReturn> returnList) {

        this.context = context;
        this.returnList = returnList;
        this.databaseHelper = new DatabaseHelper(context);
        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public ReturnViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_purchase_return,
                        parent,
                        false);

        return new ReturnViewHolder(view);

    }

    @Override
    public void onBindViewHolder(
            @NonNull ReturnViewHolder holder,
            int position) {

        PurchaseReturn purchaseReturn = returnList.get(position);

        Supplier supplier =
                databaseHelper.getSupplierById(
                        purchaseReturn.getSupplierId(),
                        sessionManager.getUserId());

        Product product =
                databaseHelper.getProductById(
                        purchaseReturn.getProductId());

        holder.txtReturnPurchase.setText(
                "Purchase ID : " + purchaseReturn.getPurchaseId());

        if (product != null) {

            holder.txtReturnProduct.setText(
                    "Product : " + product.getProductName());

        } else {

            holder.txtReturnProduct.setText(
                    "Product : Unknown");

        }

        if (supplier != null) {

            holder.txtReturnSupplier.setText(
                    "Supplier : " + supplier.getSupplierName());

        } else {

            holder.txtReturnSupplier.setText(
                    "Supplier : Unknown");

        }

        holder.txtReturnQuantity.setText(
                "Returned Qty : " +
                        purchaseReturn.getReturnQuantity());

        holder.txtReturnAmount.setText(
                "Return Amount : ₹ " +
                        purchaseReturn.getReturnAmount());

        holder.txtReturnAmount.setTextColor(
                Color.parseColor("#D32F2F"));

        holder.txtReturnDate.setText(
                "Date : " +
                        purchaseReturn.getReturnDate());

        holder.txtReturnReason.setText(
                "Reason : " +
                        purchaseReturn.getReason());

        holder.btnDeleteReturn.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete Purchase Return")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        databaseHelper.deletePurchaseReturn(
                                purchaseReturn.getReturnId());

                        databaseHelper.increaseProductStock(
                                purchaseReturn.getProductId(),
                                purchaseReturn.getReturnQuantity());

                        returnList.remove(holder.getAdapterPosition());

                        notifyItemRemoved(holder.getAdapterPosition());

                        Toast.makeText(
                                context,
                                "Purchase Return Deleted",
                                Toast.LENGTH_SHORT).show();

                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        });

    }

    @Override
    public int getItemCount() {

        return returnList.size();

    }

    static class ReturnViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        TextView txtReturnPurchase;
        TextView txtReturnProduct;
        TextView txtReturnSupplier;
        TextView txtReturnQuantity;
        TextView txtReturnAmount;
        TextView txtReturnDate;
        TextView txtReturnReason;

        Button btnDeleteReturn;

        public ReturnViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView;

            txtReturnPurchase = itemView.findViewById(R.id.txtReturnPurchase);
            txtReturnProduct = itemView.findViewById(R.id.txtReturnProduct);
            txtReturnSupplier = itemView.findViewById(R.id.txtReturnSupplier);
            txtReturnQuantity = itemView.findViewById(R.id.txtReturnQuantity);
            txtReturnAmount = itemView.findViewById(R.id.txtReturnAmount);
            txtReturnDate = itemView.findViewById(R.id.txtReturnDate);
            txtReturnReason = itemView.findViewById(R.id.txtReturnReason);

            btnDeleteReturn = itemView.findViewById(R.id.btnDeleteReturn);

        }
    }
}
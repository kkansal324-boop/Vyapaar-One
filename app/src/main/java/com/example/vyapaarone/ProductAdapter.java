package com.example.vyapaarone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.Locale;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
        implements Filterable {


    private Context context;
    private ArrayList<Product> productList;

    private ArrayList<Product> productListFull;

    private DatabaseHelper databaseHelper;



    public ProductAdapter(Context context, ArrayList<Product> productList) {

        this.context = context;

        this.productList = new ArrayList<>(productList);

        this.productListFull = new ArrayList<>(productList);

        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.txtProductName.setText(product.getProductName());

        holder.txtQuantity.setText(
                "Current Stock : "
                        + product.getQuantity()
                        + " "
                        + product.getUnit());

        holder.txtPrice.setText(
                "Price/Unit : ₹" + product.getPricePerUnit()
                        + "\nTotal Price : ₹" + product.getTotalPrice());

        holder.txtMinMax.setText(
                "Min : " + product.getMinStock()
                        + "    Max : " + product.getMaxStock());

        // -------------------------
        // Stock Status
        // -------------------------

        double current = product.getQuantity();
        double min = product.getMinStock();
        double max = product.getMaxStock();

        if (min == 0 && max == 0) {

            holder.txtStockStatus.setText("⚪ Stock Limit Not Set");
            holder.txtStockStatus.setTextColor(Color.GRAY);

        } else if (current < min) {

            holder.txtStockStatus.setText("🟡 Low Stock");
            holder.txtStockStatus.setTextColor(Color.parseColor("#F57C00"));

        } else if (current == max) {

            holder.txtStockStatus.setText("🔵 Stock Full");
            holder.txtStockStatus.setTextColor(Color.BLUE);

        } else if (current > max) {

            holder.txtStockStatus.setText("🔴 Overstock");
            holder.txtStockStatus.setTextColor(Color.RED);

        } else {

            holder.txtStockStatus.setText("🟢 Normal");
            holder.txtStockStatus.setTextColor(Color.parseColor("#2E7D32"));

        }



        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ProductDetailsActivity.class);

            intent.putExtra("productId", product.getId());

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateList(ArrayList<Product> newList) {

        productList.clear();
        productList.addAll(newList);

        productListFull.clear();
        productListFull.addAll(newList);

        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {

        return productFilter;
    }

    private final Filter productFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<Product> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(productListFull);

            } else {

                String filterPattern = constraint.toString()
                        .toLowerCase()
                        .trim();

                for (Product product : productListFull) {

                    if (product.getProductName()
                            .toLowerCase()
                            .contains(filterPattern)) {

                        filteredList.add(product);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            productList.clear();

            productList.addAll((ArrayList<Product>) results.values);

            notifyDataSetChanged();
        }
    };

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName;
        TextView txtQuantity;
        TextView txtPrice;
        TextView txtMinMax;
        TextView txtStockStatus;

        public ProductViewHolder(@NonNull View itemView) {

            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtMinMax = itemView.findViewById(R.id.txtMinMax);
            txtStockStatus = itemView.findViewById(R.id.txtStockStatus);

        }
    }
}
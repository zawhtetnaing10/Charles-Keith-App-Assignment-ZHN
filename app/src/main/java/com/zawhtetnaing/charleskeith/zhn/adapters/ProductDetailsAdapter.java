package com.zawhtetnaing.charleskeith.zhn.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.viewholders.ProductDetailsViewHolder;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsViewHolder> {
    @NonNull
    @Override
    public ProductDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_product_details, parent, false);
        return new ProductDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }
}

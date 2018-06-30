package com.zawhtetnaing.charleskeith.zhn.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.delegates.ProductDelegate;
import com.zawhtetnaing.charleskeith.zhn.viewholders.ProductViewHolder;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private boolean isDoubleColumn;
    private ProductDelegate mProductDelegate;

    public ProductAdapter(ProductDelegate productDelegate) {
        this.mProductDelegate = productDelegate;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_product, parent, false);

        return new ProductViewHolder(view, isDoubleColumn, mProductDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void setDoubleColumn(boolean doubleColumn) {
        isDoubleColumn = doubleColumn;
    }
}

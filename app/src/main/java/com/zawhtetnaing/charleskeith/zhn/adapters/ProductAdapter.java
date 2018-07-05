package com.zawhtetnaing.charleskeith.zhn.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.data.vos.NewProductsVO;
import com.zawhtetnaing.charleskeith.zhn.delegates.ProductDelegate;
import com.zawhtetnaing.charleskeith.zhn.viewholders.ProductViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private boolean isDoubleColumn;
    private ProductDelegate mProductDelegate;
    private List<NewProductsVO> mNewProducts;

    public ProductAdapter(ProductDelegate productDelegate) {
        this.mProductDelegate = productDelegate;
        mNewProducts = new ArrayList<>();
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
        holder.setmNewProduct(mNewProducts.get(position));

    }

    @Override
    public int getItemCount() {
        return mNewProducts.size();
    }

    public void setDoubleColumn(boolean doubleColumn) {
        isDoubleColumn = doubleColumn;
        notifyDataSetChanged();
    }

    public void setmNewProducts(List<NewProductsVO> newProducts) {
        mNewProducts = newProducts;
        notifyDataSetChanged();
    }

    public void appendmNewProducts(List<NewProductsVO> newProducts) {
        mNewProducts.addAll(newProducts);
        notifyDataSetChanged();
    }
}

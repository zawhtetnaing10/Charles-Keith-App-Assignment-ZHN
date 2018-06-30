package com.zawhtetnaing.charleskeith.zhn.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.data.vos.NewProductsVO;
import com.zawhtetnaing.charleskeith.zhn.delegates.ProductDelegate;
import com.zawhtetnaing.charleskeith.zhn.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fl_product_image_container)
    FrameLayout flProductImageContainer;

    @BindView(R.id.iv_product_image)
    ImageView ivProductImage;

    @BindView(R.id.btn_new_label)
    Button btnNewLabel;

    @BindView(R.id.tv_product_category)
    TextView tvProductCategory;

    private boolean mIsDoubleColumn;
    private ProductDelegate mProductDelegate;
    private NewProductsVO mNewProduct;

    public ProductViewHolder(View itemView, boolean isDoubleColumn, final ProductDelegate productDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mProductDelegate = productDelegate;
        mIsDoubleColumn = isDoubleColumn;
        if (mIsDoubleColumn == true) {
            changeDoubleColumnDimension();
        } else if (mIsDoubleColumn == false) {
            changeSingleColumnDimension();
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDelegate.onTapProduct(mNewProduct);
            }
        });
    }

    private void changeDoubleColumnDimension() {

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) flProductImageContainer.getLayoutParams();
        params.height = dpToPixel(250, flProductImageContainer);
        flProductImageContainer.setLayoutParams(params);
        flProductImageContainer.setPadding(dpToPixel(8, flProductImageContainer),
                dpToPixel(8, flProductImageContainer),
                dpToPixel(8, flProductImageContainer),
                dpToPixel(8, flProductImageContainer));


        ivProductImage.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
        ivProductImage.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;

        btnNewLabel.getLayoutParams().height = dpToPixel(30, btnNewLabel);
        btnNewLabel.getLayoutParams().width = dpToPixel(60, btnNewLabel);

        RelativeLayout.LayoutParams tvProductCategoryLayoutParams = (RelativeLayout.LayoutParams) tvProductCategory.getLayoutParams();
        tvProductCategoryLayoutParams.setMargins(0, dpToPixel(8, tvProductCategory), 0, dpToPixel(32, tvProductCategory));
        tvProductCategory.setLayoutParams(tvProductCategoryLayoutParams);

    }

    private void changeSingleColumnDimension() {

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) flProductImageContainer.getLayoutParams();
        params.height = dpToPixel(400, flProductImageContainer);
        flProductImageContainer.setLayoutParams(params);
        flProductImageContainer.setPadding(dpToPixel(32, flProductImageContainer),
                dpToPixel(16, flProductImageContainer),
                dpToPixel(32, flProductImageContainer),
                dpToPixel(16, flProductImageContainer));


        ivProductImage.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
        ivProductImage.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;

        btnNewLabel.getLayoutParams().height = dpToPixel(30, btnNewLabel);
        btnNewLabel.getLayoutParams().width = FrameLayout.LayoutParams.WRAP_CONTENT;

        RelativeLayout.LayoutParams tvProductCategoryLayoutParams = (RelativeLayout.LayoutParams) tvProductCategory.getLayoutParams();
        tvProductCategoryLayoutParams.setMargins(0, 8, 0, 32);
        tvProductCategory.setLayoutParams(tvProductCategoryLayoutParams);

    }

    public void setmNewProduct(NewProductsVO mNewProduct) {
        this.mNewProduct = mNewProduct;
        GlideApp.with(ivProductImage.getContext())
                .load(mNewProduct.getProductImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(ivProductImage);
        tvProductCategory.setText(mNewProduct.getProductTitle());
    }

    private int dpToPixel(int i, View view) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, view.getResources().getDisplayMetrics());
    }


}

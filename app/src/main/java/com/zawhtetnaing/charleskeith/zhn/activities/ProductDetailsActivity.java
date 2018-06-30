package com.zawhtetnaing.charleskeith.zhn.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.adapters.NextItemAdapter;
import com.zawhtetnaing.charleskeith.zhn.adapters.ProductDetailsAdapter;
import com.zawhtetnaing.charleskeith.zhn.data.models.ProductModel;
import com.zawhtetnaing.charleskeith.zhn.data.vos.NewProductsVO;
import com.zawhtetnaing.charleskeith.zhn.utils.ProductsConstants;
import com.zawhtetnaing.charleskeith.zhn.viewpods.EmptyViewPod;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_product_details_list)
    RecyclerView rvProductDetailsList;

    @BindView(R.id.rv_product_details_next_items)
    RecyclerView rvProductDetailsNextItems;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }

        int productId = getIntent().getIntExtra(ProductsConstants.PRODUCT_ID_EXTRA,0);
        NewProductsVO newProduct = ProductModel.getObjInstance().getNewProductById(productId);
        if(newProduct!=null){

        }else {
            coordinatorLayout.setVisibility(View.GONE);
            vpEmpty.setVisibility(View.VISIBLE);
        }

        vpEmpty.setEmptyData(R.drawable.notdatafound,getString(R.string.new_product_details_error));

        ProductDetailsAdapter productDetailsAdapter = new ProductDetailsAdapter();
        rvProductDetailsList.setAdapter(productDetailsAdapter);
        rvProductDetailsList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        NextItemAdapter nextItemAdapter = new NextItemAdapter();
        rvProductDetailsNextItems.setAdapter(nextItemAdapter);
        rvProductDetailsNextItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
    }
}

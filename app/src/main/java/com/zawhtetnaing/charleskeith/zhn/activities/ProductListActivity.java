package com.zawhtetnaing.charleskeith.zhn.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.adapters.ProductAdapter;
import com.zawhtetnaing.charleskeith.zhn.data.models.ProductModel;
import com.zawhtetnaing.charleskeith.zhn.data.vos.NewProductsVO;
import com.zawhtetnaing.charleskeith.zhn.delegates.ProductDelegate;
import com.zawhtetnaing.charleskeith.zhn.events.APIErrorEvent;
import com.zawhtetnaing.charleskeith.zhn.events.SuccessForceRefreshGetNewProductsEvent;
import com.zawhtetnaing.charleskeith.zhn.events.SuccessGetNewProductsEvent;
import com.zawhtetnaing.charleskeith.zhn.utils.ProductsConstants;
import com.zawhtetnaing.charleskeith.zhn.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends AppCompatActivity implements ProductDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_product_list)
    RecyclerView rvProductList;

    @BindView(R.id.v_underline_single_column)
    View vUnderlineSingleColumn;

    @BindView(R.id.v_underline_double_column)
    View vUnderlineDoubleColumn;

    @BindView(R.id.tv_number_of_items)
    TextView numberOfItems;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    private RecyclerView.LayoutManager layoutManager;
    private boolean isDoubleColumn = true;
    private ProductAdapter adapter;
    private boolean endOfPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        rvProductList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean isListEndReached;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                super.onScrollStateChanged(recyclerView, scrollState);
                Log.d("", "OnScrollListener : onStateChanged " + scrollState);
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE
                        && ((GridLayoutManager) rvProductList.getLayoutManager()).
                        findLastCompletelyVisibleItemPosition() == rvProductList.getAdapter().getItemCount() - 1
                        && isListEndReached != true) {
                    isListEndReached = true;
                    ProductModel.getObjInstance().loadNewProductsList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = rvProductList.getLayoutManager().getChildCount();
                int totalItemCount = rvProductList.getLayoutManager().getItemCount();
                int pastVisibleItemCount = ((GridLayoutManager) rvProductList.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisibleItemCount) < totalItemCount) {
                    isListEndReached = false;
                }
            }
        });

        numberOfItems.setMovementMethod(new ScrollingMovementMethod());

        adapter = new ProductAdapter(this);
        adapter.setDoubleColumn(isDoubleColumn);
        rvProductList.setAdapter(adapter);
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);

        rvProductList.setLayoutManager(layoutManager);

        ProductModel.getObjInstance().loadNewProductsList();

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProductModel.getObjInstance().forceRefreshProductList();
            }
        });

        vpEmpty.setEmptyData(R.drawable.notdatafound, getString(R.string.new_product_error));
    }

    @OnClick({R.id.btn_single_column, R.id.btn_double_column})
    void changeColumn(View view) {
        if (view.getId() == R.id.btn_single_column) {
            vUnderlineDoubleColumn.setVisibility(View.INVISIBLE);
            vUnderlineSingleColumn.setVisibility(View.VISIBLE);
            isDoubleColumn = false;
            adapter.setDoubleColumn(isDoubleColumn);
            rvProductList.setAdapter(adapter);
            rvProductList.setLayoutManager(new GridLayoutManager(getApplication(), 1));

        } else if (view.getId() == R.id.btn_double_column) {
            vUnderlineSingleColumn.setVisibility(View.INVISIBLE);
            vUnderlineDoubleColumn.setVisibility(View.VISIBLE);
            isDoubleColumn = true;
            adapter.setDoubleColumn(isDoubleColumn);
            rvProductList.setAdapter(adapter);
            rvProductList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapProduct(NewProductsVO newProduct) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(ProductsConstants.PRODUCT_ID_EXTRA, newProduct.getProductId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event) {
        adapter.appendmNewProducts(event.getNewProducts());
        numberOfItems.setText(adapter.getItemCount() + " ITEMS");
        swipeRefreshLayout.setRefreshing(false);
        endOfPage = true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshGetNewProducts(SuccessForceRefreshGetNewProductsEvent event) {
        adapter.setmNewProducts(event.getNewProducts());
        numberOfItems.setText(adapter.getItemCount() + " ITEMS");
        swipeRefreshLayout.setRefreshing(false);

        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailureGetNewProducts(APIErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);

        if (!endOfPage) {
            vpEmpty.setVisibility(View.VISIBLE);
        }
    }


}

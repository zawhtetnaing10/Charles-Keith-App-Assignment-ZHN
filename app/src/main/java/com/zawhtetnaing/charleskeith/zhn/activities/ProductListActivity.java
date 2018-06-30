package com.zawhtetnaing.charleskeith.zhn.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.adapters.ProductAdapter;
import com.zawhtetnaing.charleskeith.zhn.delegates.ProductDelegate;

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

    private RecyclerView.LayoutManager layoutManager;
    private boolean isDoubleColumn = true;
    private ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        numberOfItems.setMovementMethod(new ScrollingMovementMethod());

        adapter = new ProductAdapter(this);
        adapter.setDoubleColumn(isDoubleColumn);
        rvProductList.setAdapter(adapter);
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);

        rvProductList.setLayoutManager(layoutManager);

    }

    @OnClick({R.id.btn_single_column, R.id.btn_double_column})
    void changeColumn(View view) {
        if (view.getId() == R.id.btn_single_column) {
            vUnderlineDoubleColumn.setVisibility(View.INVISIBLE);
            vUnderlineSingleColumn.setVisibility(View.VISIBLE);
            isDoubleColumn = false;
            adapter.setDoubleColumn(isDoubleColumn);
            rvProductList.setAdapter(adapter);
            rvProductList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

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
    public void onTapProduct() {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        startActivity(intent);
    }
}

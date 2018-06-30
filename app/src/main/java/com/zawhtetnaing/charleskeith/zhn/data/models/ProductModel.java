package com.zawhtetnaing.charleskeith.zhn.data.models;

import com.zawhtetnaing.charleskeith.zhn.data.vos.NewProductsVO;
import com.zawhtetnaing.charleskeith.zhn.events.SuccessForceRefreshGetNewProductsEvent;
import com.zawhtetnaing.charleskeith.zhn.events.SuccessGetNewProductsEvent;
import com.zawhtetnaing.charleskeith.zhn.network.ProductsDataAgent;
import com.zawhtetnaing.charleskeith.zhn.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductModel {
    private static ProductModel objInstance;
    private ProductsDataAgent mDataAgent;
    private Map<Integer, NewProductsVO> mNewProductsMap;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private int mPage = 1;

    private ProductModel() {
        mNewProductsMap = new HashMap<>();
        EventBus.getDefault().register(this);
        mDataAgent = RetrofitDataAgentImpl.getInstance();
    }

    public static ProductModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new ProductModel();
        }
        return objInstance;
    }

    public NewProductsVO getNewProductById(int productId) {

        return mNewProductsMap.get(productId);
    }

    public void loadNewProductsList() {
        mDataAgent.loadNewProductsList(mPage, DUMMY_ACCESS_TOKEN, false);
    }

    public void forceRefreshProductList() {
        mPage = 1;
        mDataAgent.loadNewProductsList(mPage, DUMMY_ACCESS_TOKEN, true);

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event) {
        setDataIntoRepository(event.getNewProducts());
        mPage++;
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshGetNewProducts(SuccessForceRefreshGetNewProductsEvent event) {
        setDataIntoRepository(event.getNewProducts());
    }


    private void setDataIntoRepository(List<NewProductsVO> newProducts) {
        for (NewProductsVO newProduct : newProducts) {
            mNewProductsMap.put(newProduct.getProductId(), newProduct);
        }
    }
}

package com.zawhtetnaing.charleskeith.zhn.events;

import com.zawhtetnaing.charleskeith.zhn.data.vos.NewProductsVO;

import java.util.List;

public class SuccessForceRefreshGetNewProductsEvent {
    private List<NewProductsVO> newProducts;

    public SuccessForceRefreshGetNewProductsEvent(List<NewProductsVO> newProducts) {
        this.newProducts = newProducts;
    }

    public List<NewProductsVO> getNewProducts() {
        return newProducts;
    }
}

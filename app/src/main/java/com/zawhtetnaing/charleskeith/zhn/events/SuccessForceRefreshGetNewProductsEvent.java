package com.zawhtetnaing.charleskeith.zhn.events;

import com.zawhtetnaing.charleskeith.zhn.data.vos.NewProductsVO;

import java.util.List;

public class SuccessForceRefreshGetNewProductsEvent extends SuccessGetNewProductsEvent {
    public SuccessForceRefreshGetNewProductsEvent(List<NewProductsVO> newProducts) {
        super(newProducts);
    }
}

package com.zawhtetnaing.charleskeith.zhn.data.models;

public class ProductModel {
    private static ProductModel objInstance;

    private ProductModel() {

    }

    public static ProductModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new ProductModel();
        }
        return objInstance;
    }
}

package com.zawhtetnaing.charleskeith.zhn.network;

public interface ProductsDataAgent {
    void loadNewProductsList(int page, String accessToken,boolean isForceRefreshed);
}

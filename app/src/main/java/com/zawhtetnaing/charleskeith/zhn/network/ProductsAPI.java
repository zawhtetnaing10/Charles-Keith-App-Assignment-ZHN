package com.zawhtetnaing.charleskeith.zhn.network;

import com.zawhtetnaing.charleskeith.zhn.network.responses.GetNewProductsResponse;
import com.zawhtetnaing.charleskeith.zhn.utils.ProductsConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProductsAPI {
    @FormUrlEncoded
    @POST(ProductsConstants.GET_NEW_PRODUCTS)
    Call<GetNewProductsResponse> loadNewProductsList(@Field(ProductsConstants.PARAM_ACCESS_TOKEN) String accessToken,
                                                     @Field(ProductsConstants.PARAM_PAGE) int page);
}

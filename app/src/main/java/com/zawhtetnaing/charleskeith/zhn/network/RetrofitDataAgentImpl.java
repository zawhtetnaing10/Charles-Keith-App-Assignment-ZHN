package com.zawhtetnaing.charleskeith.zhn.network;

import com.zawhtetnaing.charleskeith.zhn.events.APIErrorEvent;
import com.zawhtetnaing.charleskeith.zhn.events.SuccessForceRefreshGetNewProductsEvent;
import com.zawhtetnaing.charleskeith.zhn.events.SuccessGetNewProductsEvent;
import com.zawhtetnaing.charleskeith.zhn.network.responses.GetNewProductsResponse;
import com.zawhtetnaing.charleskeith.zhn.utils.ProductsConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements ProductsDataAgent {
    private static RetrofitDataAgentImpl sObjInstance;

    private ProductsAPI mApi;

    private RetrofitDataAgentImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductsConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mApi = retrofit.create(ProductsAPI.class);
    }

    public static RetrofitDataAgentImpl getInstance() {
        if (sObjInstance == null) {
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadNewProductsList(int page, String accessToken, final boolean isForceRefreshed) {
        Call<GetNewProductsResponse> loadNewProductsCall = mApi.loadNewProductsList(accessToken, page);
        loadNewProductsCall.enqueue(new Callback<GetNewProductsResponse>() {

            @Override
            public void onResponse(Call<GetNewProductsResponse> call, Response<GetNewProductsResponse> response) {
                GetNewProductsResponse newProductsResponse = response.body();
                if (newProductsResponse != null && newProductsResponse.isResponseOk()) {
                    if(isForceRefreshed){
                        SuccessForceRefreshGetNewProductsEvent event = new SuccessForceRefreshGetNewProductsEvent(newProductsResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }else{
                        SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(newProductsResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }

                } else {
                    if (newProductsResponse == null) {
                        APIErrorEvent event = new APIErrorEvent("Empty response in network call");
                        EventBus.getDefault().post(event);
                    } else {
                        APIErrorEvent event = new APIErrorEvent(newProductsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewProductsResponse> call, Throwable t) {
                APIErrorEvent event = new APIErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}

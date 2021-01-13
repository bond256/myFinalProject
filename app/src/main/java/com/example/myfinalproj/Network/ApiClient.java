package com.example.myfinalproj.Network;

import android.content.Context;

import com.example.myfinalproj.SessionManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private Retrofit retrofit;
    private ApiService apiService;
    private SessionManager sessionManager;

    public ApiService getApiClient(Context context) {

        sessionManager = new SessionManager(context);
        if (sessionManager.getAuthToken() != null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthentificationInterseptor(context))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.screenlife.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.screenlife.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return apiService = retrofit.create(ApiService.class);
    }

}

package com.example.myfinalproj.Network;

import android.content.Context;

import com.example.myfinalproj.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthentificationInterseptor implements Interceptor {
    private Context mContext;
    private SessionManager sessionManager;

    public AuthentificationInterseptor(Context context) {
        mContext = context;
        sessionManager = new SessionManager(mContext);
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("Authorization", "Bearer " + sessionManager.getAuthToken());
        return chain.proceed(requestBuilder.build());
    }
}

package com.example.myfinalproj.Network;

import com.example.myfinalproj.model.authorization.CreateData;
import com.example.myfinalproj.model.authorization.CreateRequest;
import com.example.myfinalproj.model.authorization.LoginData;
import com.example.myfinalproj.model.authorization.LoginRequest;
import com.example.myfinalproj.model.authorization.LogoutRequest;
import com.example.myfinalproj.model.channels.Channels;
import com.example.myfinalproj.model.user.UserData;
import com.example.myfinalproj.model.videos.SearchData;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/get-channels")
    Single<Channels> getChannels();

    @POST("api-mobile/user/login")
    Single<Response<LoginData>> login(@Body LoginRequest request);

    @POST("api-mobile/user/logout")
    Completable logout(@Body LogoutRequest logoutRequest);

    @GET("api-mobile/user/get-by-id")
    Single<Response<UserData>> getUser(@Query("id") int id);

    @POST("api-mobile/user/create")
    Single<Response<CreateData>> createUser(@Body CreateRequest createRequest);

    @GET("api-mobile/search")
    Single<SearchData> getChannelsId(@Query("channel") int id);


}

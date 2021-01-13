package com.example.myfinalproj.Network;

import android.content.Context;

import com.example.myfinalproj.model.channels.Channels;
import com.example.myfinalproj.model.videos.SearchData;

import io.reactivex.schedulers.Schedulers;

public class DataService {
    private ApiService apiService;

    public DataService(Context context) {
        ApiClient apiClient = new ApiClient();
        apiService = apiClient.getApiClient(context);
    }

    public Channels getChannels() {
        return apiService.getChannels()
                .subscribeOn(Schedulers.io())
                .blockingGet();
    }

    public SearchData getSearchChannels(int id) {
        return apiService.getChannelsId(id)
                .subscribeOn(Schedulers.io())
                .blockingGet();
    }

}

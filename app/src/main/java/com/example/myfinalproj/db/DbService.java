package com.example.myfinalproj.db;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class DbService {
    private DataBase dataBase;
    private ChannelsDAO channelsDAO;

    public DbService(Context context) {
        dataBase = Room.databaseBuilder(context, DataBase.class, "channels")
                .build();
        channelsDAO = dataBase.channelsDAO();
    }

    public void insert(List<Channel> channels) {
        channelsDAO.insert(channels)
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void delete() {
        channelsDAO.delete()
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public List<Channel> getChannels() {
        return channelsDAO.getChannels()
                .subscribeOn(Schedulers.io())
                .blockingGet();
    }
}

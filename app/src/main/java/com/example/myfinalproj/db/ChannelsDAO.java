package com.example.myfinalproj.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ChannelsDAO {

    @Query("Select * FROM Channel")
    Single<List<Channel>> getChannels();

    @Insert
    Completable insert(List<Channel> channels);

    @Query("DELETE FROM Channel")
    Completable delete();
}

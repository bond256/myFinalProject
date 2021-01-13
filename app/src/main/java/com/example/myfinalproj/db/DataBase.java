package com.example.myfinalproj.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Channel.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract ChannelsDAO channelsDAO();
}

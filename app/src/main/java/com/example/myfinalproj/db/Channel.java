package com.example.myfinalproj.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Channel {
    @PrimaryKey
    public Integer id;

    public String title;
}

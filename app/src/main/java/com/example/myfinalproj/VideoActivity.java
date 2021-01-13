package com.example.myfinalproj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalproj.Network.DataService;
import com.example.myfinalproj.adapters.SearchAdapter;
import com.example.myfinalproj.model.videos.SearchData;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        RecyclerView videoRecycler = findViewById(R.id.videoRecycler);
        DataService dataService = new DataService(this);
        int id = getIntent().getIntExtra("id_channel", 0);
        SearchData searchData = dataService.getSearchChannels(id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SearchAdapter searchAdapter = new SearchAdapter(searchData);
        videoRecycler.setLayoutManager(layoutManager);
        videoRecycler.setAdapter(searchAdapter);
    }
}
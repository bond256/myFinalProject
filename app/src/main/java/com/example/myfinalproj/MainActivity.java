package com.example.myfinalproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalproj.Network.ApiClient;
import com.example.myfinalproj.Network.ApiService;
import com.example.myfinalproj.Network.DataService;
import com.example.myfinalproj.adapters.ChannelsAdapter;
import com.example.myfinalproj.db.Channel;
import com.example.myfinalproj.db.DbService;
import com.example.myfinalproj.model.channels.Channels;
import com.example.myfinalproj.model.channels.Information;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChannelsAdapter.OnClickListener {
    private ChannelsAdapter channelsAdapter;
    private RecyclerView channelRecycler;
    private LinearLayoutManager linearLayoutManager;
    private List<Channel> listChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        channelRecycler = findViewById(R.id.channels_list);
        setSupportActionBar(toolbar);
        DataService dataService = new DataService(this);
        DbService dbService = new DbService(this);
        if (dbService.getChannels().isEmpty()) {
            Channels channels = dataService.getChannels();
            dbService.insert(getChannelsList(channels));
            listChannel = getChannelsList(channels);
        } else {
            listChannel = dbService.getChannels();
        }
        channelsAdapter = new ChannelsAdapter(listChannel, MainActivity.this);
        linearLayoutManager = new LinearLayoutManager(this);
        channelRecycler.setLayoutManager(linearLayoutManager);
        channelRecycler.setAdapter(channelsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.account) {
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra("id_channel", listChannel.get(position).id);
        startActivity(intent);
    }

    private ArrayList<Channel> getChannelsList(Channels channels) {
        ArrayList<Channel> channelArrayList = new ArrayList<>();
        for (Information result : channels.getData()) {
            Channel channel = new Channel();
            channel.id = result.getId();
            channel.title = result.getTitle();
            channelArrayList.add(channel);
        }
        return channelArrayList;
    }
}
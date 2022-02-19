package com.example.thmusicmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Music> musicArrayList;
    private ListView listView_songs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView_songs = findViewById(R.id.listViewSongs);
        musicArrayList = new ArrayList();
        musicArrayList.add(new Music("All_time_winner", "Gill", R.raw.all_time_winner));
        musicArrayList.add(new Music("Sang xịn mịn", "Gill", R.raw.sang_xin_min_remix));
//        musicArrayList.add(new Music("Anh muốn đưa", "ca sĩ 4", R.raw.amdevk));
//        musicArrayList.add(new Music("Bài hát 5", "ca sĩ 5", R.raw.epduyen));
//        musicArrayList.add(new Music("Bài hát 6", "ca sĩ 6", R.raw.lalay));
//        musicArrayList.add(new Music("Bài hát 7", "ca sĩ 7", R.raw.matchanah));
//        musicArrayList.add(new Music("Bài hát 8", "Lê Thành Long", R.raw.pdl));
//        musicArrayList.add(new Music("Bài hát 9", "ca sĩ 9", R.raw.xdnm));
//        musicArrayList.add(new Music("Bài hát 10", "Lê Thành Long", R.raw.mrmsc));
//        musicArrayList.add(new Music("Em băng qua", "Lập nguyên", R.raw.embangqua));
        MusicAdapter adapter = new MusicAdapter(this, R.layout.songs_item, musicArrayList);
        listView_songs.setAdapter(adapter);
    }
}
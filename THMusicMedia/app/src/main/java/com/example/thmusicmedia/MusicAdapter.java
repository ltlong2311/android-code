package com.example.thmusicmedia;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {
    private final Context context;
    private final int layout;
    private final ArrayList<Music> musicArrayList;
    private MediaPlayer mediaPlayer;
    private boolean flag = true;

    public MusicAdapter(Context context, int layout, ArrayList<Music> musicArrayList) {
        this.context = context;
        this.layout = layout;
        this.musicArrayList = musicArrayList;
    }

    @Override
    public int getCount() {
        return musicArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return musicArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView textViewSongName, textViewArtist;
        ImageView imageView_play, imageView_stop;
        private final View convertView;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        public ViewHolder (){
            convertView = layoutInflater.inflate(layout, null);
            textViewSongName = convertView.findViewById(R.id.textViewSongs);
            textViewArtist = convertView.findViewById(R.id.textViewArtist);
            imageView_play = convertView.findViewById(R.id.imageViewPlay);
            imageView_stop = convertView.findViewById(R.id.imageViewStop);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            convertView = viewHolder.convertView;
        }
        final Music music = musicArrayList.get(position);
        viewHolder.textViewSongName.setText(music.getSongName());
        viewHolder.textViewArtist.setText(music.getArtist());
        viewHolder.imageView_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    mediaPlayer = MediaPlayer.create(context,music.getSong());
                    flag = false;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    viewHolder.imageView_play.setImageResource(R.drawable.pause0);
                }else {
                    mediaPlayer.start();
                    viewHolder.imageView_play.setImageResource(R.drawable.play2);
                    viewHolder.imageView_stop.setImageResource(R.drawable.stop);
                }
                mediaPlayer.start();
            }
        });
        viewHolder.imageView_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag = true;
                }
                viewHolder.imageView_stop.setImageResource(R.drawable.stop2);
            }
        });

        return convertView;
    }
}

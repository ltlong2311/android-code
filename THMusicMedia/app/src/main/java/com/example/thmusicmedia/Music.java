package com.example.thmusicmedia;

public class Music {
    private String songName;
    private String artist;
    private int song;

    public Music(String songName, String artist, int song) {
        this.songName = songName;
        this.artist = artist;
        this.song = song;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }
}

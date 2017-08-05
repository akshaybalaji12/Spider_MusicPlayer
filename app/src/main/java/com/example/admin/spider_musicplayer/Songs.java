package com.example.admin.spider_musicplayer;

import android.media.MediaPlayer;

/**
 * Created by Admin on 15-07-2017.
 */

public class Songs {

    String name;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    MediaPlayer mediaPlayer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}

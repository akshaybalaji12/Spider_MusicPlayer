package com.example.admin.spider_musicplayer;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.ohoussein.playpause.PlayPauseView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static CustomText name;
    static RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private SongsAdapter songsAdapter;
    static ArrayList<Songs> sList = new ArrayList<>();
    static CardView cardView;
    static RelativeLayout relativeLayout;
    static Boolean check = true;
    static SeekBar progressBar;
    static int k = 0,j =0,i = 0,c = 0;
    static PlayPauseView playPauseView;
    static MediaPlayer mp;
    private static ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (SeekBar) findViewById(R.id.seekBar);
        name = (CustomText) findViewById(R.id.textView4);
        cardView = (CardView) findViewById(R.id.cv);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        img = (ImageView) findViewById(R.id.imageView2);
        recyclerView = (RecyclerView) findViewById(R.id.songs);
        songsAdapter = new SongsAdapter(sList);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(songsAdapter);
        show();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    if(c==0){
                        Toast.makeText(getApplicationContext(),"SELECT SONG FIRST",Toast.LENGTH_SHORT).show();
                    }
                    relativeLayout.setVisibility(View.GONE);
                    check = false;
                } else{
                    c++;
                    relativeLayout.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(mp.getCurrentPosition());
                            progressBar.setMax(mp.getDuration());
                            new Handler().postDelayed(this,10);
                        }
                    },10);
                    check = true;
                }

            }
        });

        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mp!=null && fromUser){
                    mp.seekTo(progress);
                    progressBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    private void show() {
        sList.clear();
        songsAdapter.notifyDataSetChanged();
        Songs songs = new Songs();

        String n = "Po Nee Po";
        MediaPlayer song1 = MediaPlayer.create(getApplicationContext(),R.raw.music);
        set(songs,n,song1,R.drawable.images1);

        String m = "Neeyum Naanum";
        MediaPlayer song2 = MediaPlayer.create(getApplication(),R.raw.music2);
        songs = new Songs();
        set(songs,m,song2,R.drawable.images3);

        String o = "Thalli Pogathey";
        MediaPlayer song3 = MediaPlayer.create(getApplication(),R.raw.music4);
        songs = new Songs();
        set(songs,o,song3,R.drawable.images2);

        String p = "Aye Sinamika";
        MediaPlayer song4 = MediaPlayer.create(getApplication(),R.raw.music3);
        songs = new Songs();
        set(songs,p,song4,R.drawable.images);

        songsAdapter.notifyDataSetChanged();
    }

    public void set(Songs s, String sn, MediaPlayer mp,int img){
        s.setName(sn);
        s.setMediaPlayer(mp);
        s.setImage(img);
        sList.add(s);
    }

    static void getN(int n){
        name.setText("PLAYING - "+ sList.get(n).getName());
        img.setBackgroundResource(sList.get(n).getImage());
    }

    static void checkPlay(int index, PlayPauseView view){

        mp = sList.get(index).getMediaPlayer();
        i = index;

        if(sList.get(index).getMediaPlayer().isPlaying()){
            sList.get(index).getMediaPlayer().pause();
            name.setText("PAUSED - "+sList.get(index).getName());
            view.toggle();
            k = index;
            playPauseView = view;
        } else if(sList.get(k).getMediaPlayer().isPlaying()){
            sList.get(k).getMediaPlayer().stop();
            try {
                sList.get(k).getMediaPlayer().prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sList.get(index).getMediaPlayer().start();
            view.toggle();
            playPauseView.toggle();
            playPauseView = view;
            k = index;
        } else {
            sList.get(index).getMediaPlayer().start();
            view.toggle();
            k = index;
            playPauseView = view;
        }

        completed(mp,view,index);
    }

    private static void completed(MediaPlayer mp, final PlayPauseView view, final int current) {

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                view.toggle();
                name.setText(sList.get(current).getName());
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        c = 0;
    }

}

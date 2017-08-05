package com.example.admin.spider_musicplayer;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ohoussein.playpause.PlayPauseView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 15-07-2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {

    static ArrayList<Songs> sList;
    int lastPos = 0;
    int pos;

    public SongsAdapter(ArrayList<Songs> sList){
        this.sList = sList;
    }

    public void getP(int p){
        this.pos = p;
    }

    @Override
    public SongsAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_individual,parent,false);


        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final SongsAdapter.MyViewHolder holder, final int position) {

        holder.sName.setText(sList.get(position).getName());
        holder.imageView.setBackgroundResource(sList.get(position).getImage());
        holder.playPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.c++;
                MainActivity.getN(position);
                getP(position);
                MainActivity.checkPlay(position,holder.playPauseView);
            }
        });
        lastPos = position;
        MainActivity.j = lastPos;
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public CustomText sName;
        public PlayPauseView playPauseView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            sName = (CustomText) itemView.findViewById(R.id.textView);
            playPauseView = (PlayPauseView) itemView.findViewById(R.id.play_pause);

        }
    }

    /*public void onClick(final View view){
        int itemPos = MainActivity.recyclerView.getChildAdapterPosition(view);

    }*/

}

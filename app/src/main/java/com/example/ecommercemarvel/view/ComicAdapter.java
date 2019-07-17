package com.example.ecommercemarvel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.model.Comic;
import com.example.ecommercemarvel.model.ComicFacade;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.MyViewHolder> {

    private List<Comic> comics;
    private Context context;

    public ComicAdapter(Context context) {
        this.context = context;

    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }

    @NonNull
    @Override
    public ComicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View comicView =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comic_adapter, viewGroup, false);
        Log.i("tela", "tela");
        return new MyViewHolder(comicView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicAdapter.MyViewHolder myViewHolder, int i) {
        Comic comic = this.comics.get(i);

        Log.i("title hq", comic.getTitle());
        Log.i("index", ""+i);
        Log.i("tamanho", ""+this.comics.size());

        Log.i("url", comic.getUrlImage());

        Glide.with(context)
                .load(comic.getUrlImage())
                .placeholder(R.drawable.tony)
                .centerCrop()
                .into(myViewHolder.comicImage);

        myViewHolder.comicTitle.setText(comic.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.comics.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        final TextView comicTitle;
        final ImageView comicImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comicTitle = itemView.findViewById(R.id.comicTitle);
            comicImage = itemView.findViewById(R.id.comicImage);
        }


    }
}



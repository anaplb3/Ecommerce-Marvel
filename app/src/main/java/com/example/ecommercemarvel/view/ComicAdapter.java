package com.example.ecommercemarvel.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.ecommercemarvel.contentProvider.ComicContract;
import com.example.ecommercemarvel.contentProvider.ComicFacade;
import com.example.ecommercemarvel.model.Comic;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.MyViewHolder> {

    private List<Comic> comics;
    private Context context;
    private Comic comic;
    private Comic previousComic;

    //Pegar aqui no construtor, talvez
    public ComicAdapter(Context context) {
        this.context = context;

    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }

    @NonNull
    @Override
    public ComicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View comicView =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new MyViewHolder(comicView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicAdapter.MyViewHolder myViewHolder, int i) {

        // Evitando que ele passe o quadrinho errado para a próxima activity (ComicDetails)
        try {
            previousComic = this.comics.get(i-1);
        } catch (IndexOutOfBoundsException error) {
            previousComic = this.comics.get(i);
        }
        
        comic = this.comics.get(i);

        //Aqui tb
        Glide.with(context)
                .load(comic.getUrlImage())
                .placeholder(R.drawable.tony)
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

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            comicTitle = itemView.findViewById(R.id.txt_title_hq);
            comicImage = itemView.findViewById(R.id.img_hq);

            comicImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ComicDetailsActivity.class);
                    intent.putExtra("comicObject", previousComic);
                    intent.putExtra("comicURI", ComicContract.ComicEntry.buildComicUriWithId(comics.indexOf(comic)).toString());
                    Log.i("comic uri",  ComicContract.ComicEntry.buildComicUriWithId(comics.indexOf(comic)).toString());
                    context.startActivity(intent);
                }
            });

            }

    }
}



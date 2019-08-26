package com.example.ecommercemarvel.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.contentProvider.ComicContract;
import com.example.ecommercemarvel.contentProvider.ComicFacade;
import com.example.ecommercemarvel.contentProvider.ComicProvider;
import com.example.ecommercemarvel.model.Comic;

import java.util.List;


import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder> {
    List<Comic> checkoutComics;
    private Context context;
    private Comic comic;
    public RequestOptions options;
    ComicFacade comicFacade;

    public CheckoutAdapter(Context context, List<Comic> checkoutComics) {
        this.context = context;
        this.checkoutComics = checkoutComics;
        this.comicFacade = new ComicFacade(context);
        //aqui
        this.options = new RequestOptions();
        options.placeholder(R.drawable.tony)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View comicView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list, viewGroup, false);
        return new MyViewHolder(comicView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        comic = this.checkoutComics.get(i);


        Glide.with(context)
                .load(comic.getUrlImage())
                .placeholder(R.drawable.tony)
                .apply(options)
                .into(myViewHolder.comicImage);
        //v();
        //myViewHolder.comicTitleCheckout.setText(comic.getTitle());

    }

    public void v() {

        Comic comiic = null;
        try {
            comiic = comicFacade.getComic( ComicContract.ComicEntry.buildComicUriWithId(0) );
            Log.i("checkout holder",comiic.getTitle());
        } catch (Exception e) {
            Log.i("errooo", e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return this.checkoutComics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView comicImage;
        private TextView comicTitleCheckout;
        private ImageButton addComic;
        private TextView totalComics;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            comicImage = itemView.findViewById(R.id.img_comic);
            comicTitleCheckout = itemView.findViewById(R.id.comicTitleCheckout);
            addComic = itemView.findViewById(R.id.btn_add);
            totalComics = itemView.findViewById(R.id.txt_total);


            addComic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    comicFacade.deleteComic(comic.getId());
                }
            });
        }
    }
}

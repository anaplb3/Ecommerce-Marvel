package com.example.ecommercemarvel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.model.Comic;

import java.util.List;


import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder>{
    List<Comic> checkoutComics;
    private Context context;
    private Comic comic;
    public RequestOptions options;

    public CheckoutAdapter(Context context, List<Comic> checkoutComics) {
        this.context = context;
        this.checkoutComics = checkoutComics;
        this.options = new RequestOptions();
        options.placeholder(R.drawable.tony)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View comicView =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkout_adapter, viewGroup, false);
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

        myViewHolder.comicTitleCheckout.setText(comic.getTitle());

    }

    @Override
    public int getItemCount() {
        return this.checkoutComics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView comicImage;
        private TextView comicTitleCheckout;
        private Button addComic;
        private TextView totalComics;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            comicImage = itemView.findViewById(R.id.comicThumbImage);
            comicTitleCheckout = itemView.findViewById(R.id.comicTitleCheckout);
            addComic = itemView.findViewById(R.id.buttomAddComic);
            totalComics = itemView.findViewById(R.id.textTotalComics);


            addComic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

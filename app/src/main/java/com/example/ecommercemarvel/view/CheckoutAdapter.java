package com.example.ecommercemarvel.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.contentProvider.ComicFacade;
import com.example.ecommercemarvel.model.Comic;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder> {
    List<Comic> checkoutComics;
    private Context context;
    private Comic comic;
    public RequestOptions options;
    ComicFacade comicFacade;
    TextView txtTotalPrice;
    CheckoutActivity checkoutActivity;

    public CheckoutAdapter(Context context, List<Comic> checkoutComics, TextView textView, CheckoutActivity checkoutActivity) {
        this.context = context;
        this.checkoutComics = checkoutComics;
        this.comicFacade = new ComicFacade(context);
        this.options = new RequestOptions();
        options.placeholder(R.drawable.tony)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
        this.txtTotalPrice = textView;
        this.checkoutActivity = checkoutActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View comicView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list, viewGroup, false);
        return new MyViewHolder(comicView);

    }

    public void updatingQtd(TextView totalComics) {
        totalComics.setText(""+comic.getQtd());
    }

    private void settingTitle(TextView txt) {
        txt.setText(comic.getTitle());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        comic = this.checkoutComics.get(i);

        if(comic.getUrlImage().contains("image")) {
            myViewHolder.comicImage.setImageResource(R.drawable.logo_marvel_erro);
        } else {
            Glide.with(context)
                    .load(comic.getUrlImage())
                    .placeholder(R.drawable.tony)
                    .apply(options)
                    .into(myViewHolder.comicImage);
        }


        settingTitle(myViewHolder.title);
        updatingQtd(myViewHolder.totalComics);

    }


    @Override
    public int getItemCount() {
        return this.checkoutComics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView comicImage;
        private ImageButton addComic;
        private ImageButton removeComic;
        private TextView totalComics;
        private TextView title;
        private ImageButton lowerComic;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            comicImage = itemView.findViewById(R.id.img_comic);
            addComic = itemView.findViewById(R.id.btn_add);
            totalComics = itemView.findViewById(R.id.txt_total);
            title = itemView.findViewById(R.id.txt_title);
            lowerComic = itemView.findViewById(R.id.btn_lower);
            removeComic = itemView.findViewById(R.id.btn_remove);

            settingPrice();
            addingComic();
            loweringComic();
            removingComic();

        }

        private void addingComic() {
            addComic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("addComic", "requested");
                    comic.addQtd();
                    updatingQtd(totalComics);
                    settingPrice();
                }
            });
        }


        private void loweringComic() {
            lowerComic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("lowerComic", "requested");
                    comic.lowerQtd();
                    updatingQtd(totalComics);
                    settingPrice();
                }
            });
        }

        private void removingComic() {
            removeComic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    comicFacade.deleteComic(comic.getId());
                    updatingQtd(totalComics);
                    settingPrice();
                    checkoutActivity.refresh();
                }
            });
        }

        public double calculatingTotalPrice() {
            double totalPrice = 0;

            for(Comic c: checkoutComics) {
                totalPrice += c.getPrice();
            }

            return totalPrice;
        }

        public void settingPrice() {
            @SuppressLint("DefaultLocale") String total = String.format("Total: R$%s", String.format("%.2f", calculatingTotalPrice()));
            try {
                txtTotalPrice.setText(total);
            } catch (Exception e) {
                Log.i("erro price", e.getMessage());
            }

        }
    }
}

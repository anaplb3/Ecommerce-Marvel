package com.example.ecommercemarvel.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.dbHelper.DbComics;
import com.example.ecommercemarvel.model.Comic;

public class ComicDetailsActivity extends AppCompatActivity {
    Comic comic;
    DbComics dbComics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_details);

        Intent intent = getIntent();

        //Possível injeção aqui
        comic = (Comic) intent.getSerializableExtra("comicObject");

        dbComics = new DbComics(getApplicationContext());

        settingImage(comic.getUrlImage());
        settingDescription(comic.getDescription());
        settingPrice(comic.getPrice());

        //Log.i("title", comic.getTitle());

        Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ComicsActivity.class);
                startActivity(it);
                finish();
            }
        });


        Button addToChart = findViewById(R.id.buttonAddToChart);
        addToChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToCart(comic.getId(), comic.getTitle(), comic.getPrice(), comic.isRare());

                Toast.makeText(getApplicationContext(), "Quadrinho adicionado no carrinho!", Toast.LENGTH_SHORT).show();
            }
        });


        Button buy = findViewById(R.id.buttonBuy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(comic.getId(), comic.getTitle(), comic.getPrice(), comic.isRare());

                Intent it = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(it);
                finish();
            }
        });


    }

    private void addToCart(int id, String title, double price, boolean isRare) {

        dbComics.addingToTheCart(comic.getId(), comic.getTitle(), comic.getPrice(), convertingBoolean(comic.isRare()));

    }

    private int convertingBoolean(boolean value) {

        return value ? 1 : 0;

    }

    private void settingImage(String imageUrl) {
        ImageView imageView = findViewById(R.id.comicDetailImage);
        Glide.with(getApplicationContext())
                .load(imageUrl)
                .placeholder(R.drawable.tony)
                .into(imageView);
    }

    private void settingDescription(String description) {
        TextView txt = findViewById(R.id.comicDetailDescription);

        if(description == null) {
            description = "Não há descrição disponível!";
        }

        txt.setText(description);
    }

    private void settingPrice(double price) {

        TextView txt = findViewById(R.id.comicDetailPrice);

        String preco = "Preço: R$"+String.format("%.2f", price);

        txt.setText(preco);
    }

}

package com.example.ecommercemarvel.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.contentProvider.ComicFacade;
import com.example.ecommercemarvel.dbHelper.DbComics;
import com.example.ecommercemarvel.model.Comic;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity{
    DbComics dbComics;
    List<Comic> checkoutComics;
    RecyclerView checkoutRecycleView;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        //Definitivamente aqui
        //dbComics = new DbComics(getApplicationContext());

        ComicFacade comicFacade = new ComicFacade(getApplicationContext());

        //checkoutComics = dbComics.getComicsInTheCart();

        checkoutComics = comicFacade.getComicsInTheCart();
        textView = findViewById(R.id.txt_total_price);

        Log.i("array size: ", ""+checkoutComics.size());

        for(Comic c: checkoutComics) {
            Log.i("title: ", c.getTitle());
        }

        checkoutRecycleView = findViewById(R.id.checkoutRecyclerView);
        //Aqui tb
        CheckoutAdapter checkoutAdapter = new CheckoutAdapter(getApplicationContext(), checkoutComics, textView, this);
        checkoutRecycleView.setAdapter(checkoutAdapter);
        checkoutRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


    }

    public void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}

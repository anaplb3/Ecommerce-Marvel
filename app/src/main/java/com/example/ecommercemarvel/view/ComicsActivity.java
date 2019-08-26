package com.example.ecommercemarvel.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.controller.MarvelService;
import com.example.ecommercemarvel.dagger.DMarvelService;
import com.example.ecommercemarvel.dagger.DaggerDMarvelService;
import com.example.ecommercemarvel.rxJava.RxJavaExample;
import com.example.ecommercemarvel.service.MarvelPoolService;

import javax.inject.Inject;

public class ComicsActivity extends AppCompatActivity {

    @Inject
    MarvelPoolService marvelPoolService;

    @Inject
    MarvelService marvelService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        DMarvelService dMarvelService = DaggerDMarvelService.builder().build();
        dMarvelService.inject(this);

        RecyclerView recyclerView = findViewById(R.id.comicRecycleView);

        RxJavaExample rx = new RxJavaExample(recyclerView, this);

        Button buttonCheckout = findViewById(R.id.buttonGoCheckout);
        buttonCheckout.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), CheckoutActivity.class);
            startActivity(it);
        });

    }

}

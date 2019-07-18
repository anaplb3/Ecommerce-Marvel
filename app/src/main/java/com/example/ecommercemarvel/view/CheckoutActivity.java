package com.example.ecommercemarvel.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.dbHelper.DbComics;
import com.example.ecommercemarvel.model.Comic;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    DbComics dbComics;
    List<Comic> checkoutComics;
    RecyclerView checkoutRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        dbComics = new DbComics(getApplicationContext());

        checkoutComics = dbComics.getComicsInTheCart();

        checkoutRecycleView = findViewById(R.id.checkoutRecyclerView);

        CheckoutAdapter checkoutAdapter = new CheckoutAdapter(getApplicationContext(), checkoutComics);
        checkoutRecycleView.setAdapter(checkoutAdapter);
        checkoutRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }
}

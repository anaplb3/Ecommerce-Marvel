package com.example.ecommercemarvel.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ecommercemarvel.R;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        ImageView img = findViewById(R.id.img_gif);

        Glide.with(this)
                .asGif()
                .load(R.drawable.loader)
                .into(img);
    }
}

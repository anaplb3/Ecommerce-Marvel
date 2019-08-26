package com.example.ecommercemarvel.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.ecommercemarvel.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //Yep
        Handler handler = new Handler();
        handler.postDelayed(this::mostrarMainActivity, 2000);
    }

    private void mostrarMainActivity() {
        Intent intent = new Intent(getApplicationContext(), ComicsActivity.class);
        startActivity(intent);
        finish();
    }
}

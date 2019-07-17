package com.example.ecommercemarvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ecommercemarvel.controller.Controller;
import com.example.ecommercemarvel.model.Comic;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller ct = new Controller();
        ct.create();

        //List<Comic> comics = ct.getResponseDTO().getData().getComics();
    }
}

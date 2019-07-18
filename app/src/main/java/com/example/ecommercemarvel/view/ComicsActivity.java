package com.example.ecommercemarvel.view;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.controller.MarvelService;
import com.example.ecommercemarvel.model.ResponseDTO;
import com.example.ecommercemarvel.service.MarvelPoolService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicsActivity extends AppCompatActivity implements Callback<ResponseDTO> {
    private RecyclerView recyclerView;
    private ResponseDTO responseDTO;
    private static final String publicKey ="b4cd443a32bbf5b36e3ef1b9337b60d0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        this.recyclerView = findViewById(R.id.comicRecycleView);

        create();

        Button buttonCheckout = findViewById(R.id.buttonGoCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(it);
            }
        });

    }


    public void create() {

        String ts = "1563301090313";
        String hash = "a06144f886aa12b6407c0c40a0167006";

        MarvelService marvelAPI = MarvelPoolService.getInstance().getService(MarvelService.class);

        Call<ResponseDTO> comics = marvelAPI.loadComics(ts, publicKey, hash);
        comics.enqueue(this);

    }



    @Override
    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {

        if(response.isSuccessful()) {


            System.out.println("conectou");


            this.responseDTO =  response.body();
            ComicAdapter comicAdapter = new ComicAdapter(this);
            comicAdapter.setComics(this.responseDTO.getData().getComics());

            recyclerView.setAdapter(comicAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            System.out.println(response.code());


        } else {

            responseDTO = null;
        }
    }


    @Override
    public void onFailure(Call<ResponseDTO> call, Throwable t) {
        responseDTO = null;
        System.out.println("não conectou");
        System.out.println(t.getMessage());
    }
}

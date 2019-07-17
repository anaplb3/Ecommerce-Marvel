package com.example.ecommercemarvel;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ecommercemarvel.controller.Controller;
import com.example.ecommercemarvel.controller.MarvelService;
import com.example.ecommercemarvel.model.ComicFacade;
import com.example.ecommercemarvel.model.ResponseDTO;
import com.example.ecommercemarvel.service.MarvelPoolService;
import com.example.ecommercemarvel.view.ComicAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicsActivity extends AppCompatActivity implements Callback<ResponseDTO> {
    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    private ResponseDTO responseDTO;
    private static final String urlBase = "https://gateway.marvel.com/";
    private static final String publicKey ="43de5ac9c8f743f13ca4e01040a03e69";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("red")));


        this.recyclerView = findViewById(R.id.comicRecycleView);

        create();

        //this.comicAdapter = new ComicAdapter();

        Log.i("aaaa", "aaaaaa");

        //recyclerView.setAdapter(comicAdapter);
    }


    public void create() {
        /*Gson gsonConverter = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();

        MarvelPoolService marvelAPI = retrofit.create(MarvelPoolService.class);*/

        String ts = "anaehumapessoaincrivelmentetop";
        String hash = "2164d8107865cd34e930ce21b2066289";

        MarvelService marvelAPI = MarvelPoolService.getInstance().getService(MarvelService.class);

        Call<ResponseDTO> comics = marvelAPI.loadComics(ts, publicKey, hash);
        comics.enqueue(this);

    }



    @Override
    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {

        Log.i("conexao", "on response");

        if(response.isSuccessful()) {

            Log.i("conexao", "succesful");

            System.out.println("conectou");


            this.responseDTO =  response.body();
            this.comicAdapter = new ComicAdapter(this);
            this.comicAdapter.setComics(this.responseDTO.getData().getComics());

            //this.comicAdapter.comics = this.responseDTO.getData().getComics();


            recyclerView.setAdapter(comicAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            System.out.println(response.code());

            for(int i = 0; i < 5; i++) {
                Log.i("hq 22", responseDTO.getData().getComics().get(i).getTitle());
            }


        } else {

            Log.i("conexao", "not succesful");
            System.out.println("conectou mas deu errado");
            responseDTO = null;
        }
    }


    @Override
    public void onFailure(Call<ResponseDTO> call, Throwable t) {
        responseDTO = null;
        Log.i("conexao", "nao conectou");
        System.out.println("não conectou");
        System.out.println(t.getMessage());
    }
}

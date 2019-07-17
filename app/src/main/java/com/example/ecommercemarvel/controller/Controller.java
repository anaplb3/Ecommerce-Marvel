package com.example.ecommercemarvel.controller;

import android.util.Log;

import com.example.ecommercemarvel.model.MarvelDTO;
import com.example.ecommercemarvel.model.Comic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<MarvelDTO> {
    private static final String URL_BASE = "https://gateway.marvel.com/v1/public/";
    private Timestamp time;
    private int ts;
    private static final String private_key ="43de5ac9c8f743f13ca4e01040a03e69";
    private static final String public_key = "7e16f91cede5704285cc8f570b5ad139c671fa1b";
    private List<Comic> comics;

    private String creating_hash() throws NoSuchAlgorithmException{

        time = new Timestamp(System.currentTimeMillis());
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Deu erro no hash!");
        }

        ts = time.getNanos();

        String chaves = ts + public_key + private_key;
        messageDigest.update(chaves.getBytes());
        byte[] digest = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1, digest);

        return bigInt.toString(16);
    }


    public void create() {
        Gson gson_converter = new GsonBuilder()
//                .setLenient()
                .create();

        String hash = null;
        try {
            hash = creating_hash();
        } catch (NoSuchAlgorithmException error) {
            System.out.println(error.getMessage());
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson_converter))
                .build();

        MarvelService marvelAPI = retrofit.create(MarvelService.class);

        Call<MarvelDTO> comics = marvelAPI.loadComics("1563301090313",
                "b4cd443a32bbf5b36e3ef1b9337b60d0",
                "a06144f886aa12b6407c0c40a0167006");
        comics.enqueue(this);

    }

    @Override
    public void onResponse(Call<MarvelDTO> call, Response<MarvelDTO> response) {
        Log.i("comic", "on response");

        Log.i("url", ""+response.raw().request().url());

        if(response.isSuccessful()) {
            MarvelDTO body = response.body();
            Log.i("response", body.getEtag());

            for(Comic comic: body.getData().getResults()) {
                Log.i(comic.getTitle(), comic.getTitle());
            }
        } else {
            Log.i("teste2", ""+response.code());
        }
    }

    @Override
    public void onFailure(Call<MarvelDTO> call, Throwable t) {
        Log.i("comic", "então né, deu erro");
        Log.i("teste", t.getMessage());
        Log.i("response", call.request().url().toString());
    }
}

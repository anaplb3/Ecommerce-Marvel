package com.example.ecommercemarvel.controller;

import android.util.Log;

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

public class Controller implements Callback<List<Comic>> {
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
                .setLenient()
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

        Call<List<Comic>> comics = marvelAPI.loadComics(str (ts), public_key, hash);
        comics.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Comic>> call, Response<List<Comic>> response) {
        Log.i("comic", "on response");

        Log.i("url", ""+response.raw().request().url());

        if(response.isSuccessful()) {
            Log.i("comic", "on response2");
            List<Comic> comics = response.body();
            if(comics.isEmpty()) {
                Log.i("comic", "tá null");
            }
            for (Comic c: comics) {
                Log.i("comic", c.getTitle());
            }
        } else {
            Log.i("comic", ""+response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Comic>> call, Throwable t) {
        Log.i("comic", "então né, deu erro");

    }
}

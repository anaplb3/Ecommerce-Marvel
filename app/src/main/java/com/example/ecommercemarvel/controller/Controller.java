package com.example.ecommercemarvel.controller;

import android.util.Log;

<<<<<<< HEAD
import com.example.ecommercemarvel.model.MarvelDTO;
import com.example.ecommercemarvel.model.Comic;
=======

import com.example.ecommercemarvel.model.ResponseDTO;
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

<<<<<<< HEAD
public class Controller implements Callback<MarvelDTO> {
    private static final String URL_BASE = "https://gateway.marvel.com/v1/public/";
    private Timestamp time;
    private int ts;
    private static final String private_key ="43de5ac9c8f743f13ca4e01040a03e69";
    private static final String public_key = "7e16f91cede5704285cc8f570b5ad139c671fa1b";
    private List<Comic> comics;

    private String creating_hash() throws NoSuchAlgorithmException{
=======
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4

public class Controller implements Callback<ResponseDTO> {
    private static final String URL_BASE = "https://gateway.marvel.com/";
    private static final String PUBLIC_KEY ="43de5ac9c8f743f13ca4e01040a03e69";

    private ResponseDTO responseDTO;

    /**
     * Método que conecta a API da Marvel
     */
    public void create() {
<<<<<<< HEAD
        Gson gson_converter = new GsonBuilder()
//                .setLenient()
=======
        Gson gsonConverter = new GsonBuilder()
                .setLenient()
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();

        MarvelService marvelAPI = retrofit.create(MarvelService.class);

<<<<<<< HEAD
        Call<MarvelDTO> comics = marvelAPI.loadComics("1563301090313",
                "b4cd443a32bbf5b36e3ef1b9337b60d0",
                "a06144f886aa12b6407c0c40a0167006");
=======
        String ts = "anaehumapessoaincrivelmentetop";
        String hush = "2164d8107865cd34e930ce21b2066289";

        Call<ResponseDTO> comics = marvelAPI.loadComics(ts, PUBLIC_KEY, hush);
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
        comics.enqueue(this);

    }

    public ResponseDTO getResponseDTO() {
        return responseDTO;
    }

    @Override
<<<<<<< HEAD
    public void onResponse(Call<MarvelDTO> call, Response<MarvelDTO> response) {
=======
    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
        Log.i("comic", "on response");

        Log.i("url", ""+response.raw().request().url());

        if(response.isSuccessful()) {
<<<<<<< HEAD
            MarvelDTO body = response.body();
            Log.i("response", body.getEtag());

            for(Comic comic: body.getData().getResults()) {
                Log.i(comic.getTitle(), comic.getTitle());
            }
=======
            Log.i("comic", "on response2");
            responseDTO = response.body();

>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
        } else {
            Log.i("teste2", ""+response.code());
        }
    }

    @Override
<<<<<<< HEAD
    public void onFailure(Call<MarvelDTO> call, Throwable t) {
        Log.i("comic", "então né, deu erro");
        Log.i("teste", t.getMessage());
        Log.i("response", call.request().url().toString());
=======
    public void onFailure(Call<ResponseDTO> call, Throwable t) {
        Log.i("comic", "então né, deu erro");
        Log.i("erro", t.getMessage());

>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
    }
}

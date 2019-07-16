package com.example.ecommercemarvel.controller;

import com.example.ecommercemarvel.model.Comic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {

    /*public MarvelService() {
        String PRIVATE_KEY = "43de5ac9c8f743f13ca4e01040a03e69";
        String PUBLIC_KEY = "7e16f91cede5704285cc8f570b5ad139c671fa1b";


        Timestamp time = new Timestamp(System.currentTimeMillis());
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert m != null;
        m.update(Byte.parseByte(time + PUBLIC_KEY + PRIVATE_KEY));
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hash = bigInt.toString(16);

        Retrofit.Builder retro = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/comics?apikey="+PUBLIC_KEY+"&hash="+hash);

    }*/


    @GET("/comics")
    Call<List<Comic>> loadComics(@Query("ts") String ts,@Query("apikey") String public_key, @Query("hash") String hash);


}

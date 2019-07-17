package com.example.ecommercemarvel.controller;

<<<<<<< HEAD
import com.example.ecommercemarvel.model.MarvelDTO;
=======

import com.example.ecommercemarvel.model.ResponseDTO;
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {



<<<<<<< HEAD
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


    @GET("comics")
    Call<MarvelDTO> loadComics(@Query("ts") String ts, @Query("apikey") String public_key, @Query("hash") String hash);
=======
    @GET("v1/public/comics")
    Call<ResponseDTO> loadComics(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);

>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4

}

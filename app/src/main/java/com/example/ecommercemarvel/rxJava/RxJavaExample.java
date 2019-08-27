package com.example.ecommercemarvel.rxJava;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ecommercemarvel.controller.MarvelService;
import com.example.ecommercemarvel.dagger.DMarvelService;
import com.example.ecommercemarvel.dagger.DaggerDMarvelService;
import com.example.ecommercemarvel.model.Comic;
import com.example.ecommercemarvel.view.ComicAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxJavaExample {

    @Inject
    MarvelService marvelService;
    private ComicAdapter comicAdapter;
    private RecyclerView recyclerView;
    private Context context;

    public RxJavaExample(RecyclerView recyclerView, Context context) {

        this.comicAdapter = new ComicAdapter(context);
        this.recyclerView = recyclerView;
        this.context = context;

        DMarvelService dMarvelService = DaggerDMarvelService.builder().build();
        dMarvelService.inject(this);
        create();
    }

    public void create() {

        String ts = "1563301090313";
        String hash = "a06144f886aa12b6407c0c40a0167006";
        String publicKey = "b4cd443a32bbf5b36e3ef1b9337b60d0";

        marvelService
                .getComics(ts, publicKey, hash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseDTO -> setting(responseDTO.getData().getComics()), throwable -> throwable.printStackTrace());

    }


    private void setting(List<Comic> comicsList) {
        this.comicAdapter.setComics(comicsList);
        this.recyclerView.setAdapter(this.comicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
    }


}

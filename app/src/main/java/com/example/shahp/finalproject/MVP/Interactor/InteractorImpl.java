package com.example.shahp.finalproject.MVP.Interactor;

import com.example.shahp.finalproject.Models.categoryList.CategoryList;
import com.example.shahp.finalproject.Service.Consts;
import com.example.shahp.finalproject.Service.RequestInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shahp on 31/07/2017.
 */

public class InteractorImpl implements  Interactor {
    static Retrofit retrofit;
    static OkHttpClient okHttpClient;
    RequestInterface requestInterface;

    public InteractorImpl() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        File outputDir = context.getCacheDir();
//        Cache cache = new Cache(outputDir, 50000);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        requestInterface = retrofit.create(RequestInterface.class);
//        return retrofit.create(RequestInterface.class);
    }

    @Override
    public Observable<CategoryList> getCategoryList() {
        return requestInterface.getCategoryList();
    }
}
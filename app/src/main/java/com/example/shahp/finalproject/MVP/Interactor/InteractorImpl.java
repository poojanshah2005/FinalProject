package com.example.shahp.finalproject.MVP.Interactor;

import android.content.Context;

import com.example.shahp.finalproject.MVP.Service.Utils;
import com.example.shahp.finalproject.Models.AlcoholicResult.AlcoholicResult;
import com.example.shahp.finalproject.Models.CategoryList.CategoryResults;
import com.example.shahp.finalproject.Models.DrinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.GlassList.GlassResults;
import com.example.shahp.finalproject.Models.IngredientResults.IngredientResults;
import com.example.shahp.finalproject.MVP.Service.Consts;
import com.example.shahp.finalproject.MVP.Service.RequestInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by shahp on 31/07/2017.
 */

/**
 * interactor
 */
public class InteractorImpl implements  Interactor {
    static Retrofit retrofit;
    static OkHttpClient okHttpClient;
    RequestInterface requestInterface;
    static Context context;

    public InteractorImpl(Context context) {
        this.context = context;

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        File outputDir = context.getCacheDir();
//        Cache cache = new Cache(outputDir, 50000);

        //setup cache
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 50 * 1024 * 1024; // 50 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        //add cache to the client

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
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

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            String cacheHeaderValue = Utils.isNetworkAvailable(context)
                    ? "public, max-age=2419200"
                    : "public, only-if-cached, max-stale=2419200" ;
            Request request = originalRequest.newBuilder().build();
            Response response = chain.proceed(request);
            return response.newBuilder()
                    .header("Cache-Control", cacheHeaderValue)
                    .build();
            }
        };


    @Override
    public Observable<CategoryResults> getCategoryList() {
        return requestInterface.getCategoryList();
    }

    @Override
    public Observable<GlassResults> getGlassList() {
        return requestInterface.getGlassList();
    }

    @Override
    public Observable<IngredientResults> getIngredientList() {
        return requestInterface.getIngredientList();
    }

    @Override
    public Observable<AlcoholicResult> getAlcoholicList() {
        return requestInterface.getAlcoholicList();
    }

    @Override
    public Observable<DrinksResult> getByCategory(String category) {
        return requestInterface.getByCategory(category);
    }

    @Override
    public Observable<DrinksResult> getByIngredient(@Query("i") String ingredient) {
        return requestInterface.getByIngredient(ingredient);
    }

    @Override
    public Observable<DrinksResult> getByAlcoholic(@Query("a") String alcoholic) {
        return requestInterface.getByAlcoholic(alcoholic);
    }

    @Override
    public Observable<DrinksResult> getByGlass(@Query("g") String glass) {
        return requestInterface.getByGlass(glass);
    }

    @Override
    public Observable<DrinkResult> getDrinkById(String id) {
        return requestInterface.getDrinkById(id);
    }
}

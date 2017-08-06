package com.example.shahp.finalproject.MVP.Interactor;

import com.example.shahp.finalproject.Models.AlcoholicResult.AlcoholicResult;
import com.example.shahp.finalproject.Models.categoryList.CategoryResults;
import com.example.shahp.finalproject.Models.drinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.glassList.GlassResults;
import com.example.shahp.finalproject.Models.ingredientResults.IngredientResults;
import com.example.shahp.finalproject.Service.Consts;
import com.example.shahp.finalproject.Service.RequestInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

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

    @Override
    public Call<DrinksResult> getCallByIngredient(String ingredient) {
        return requestInterface.getCallByIngredient(ingredient);
    }

    @Override
    public Call<DrinksResult> getCallByCategory(String category) {
        return requestInterface.getCallByCategory(category);
    }

    @Override
    public Call<DrinksResult> getCallByAlcoholic(String alcoholic) {
        return requestInterface.getCallByAlcoholic(alcoholic);
    }

    @Override
    public Call<DrinksResult> getCallByGlass(String glass) {
        return requestInterface.getCallByGlass(glass);
    }
}

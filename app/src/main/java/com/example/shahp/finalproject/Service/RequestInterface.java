package com.example.shahp.finalproject.Service;

import com.example.shahp.finalproject.Models.categoryList.CategoryResults;
import com.example.shahp.finalproject.Models.drinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.glassList.GlassResults;
import com.example.shahp.finalproject.Models.ingredientResults.IngredientResults;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shahp on 31/07/2017.
 */

public interface RequestInterface {

    @GET(Consts.CategoryList)
    Observable<CategoryResults> getCategoryList();
    @GET(Consts.GlassList)
    Observable<GlassResults> getGlassList();
    @GET(Consts.IngredientList)
    Observable<IngredientResults> getIngredientList();
    @GET(Consts.AlcoholicList)
    Observable<IngredientResults> getAlcoholicList();
    @GET(Consts.FILTER)
    Observable<DrinksResult> getByCategory(@Query("c") String category);
    @GET(Consts.FILTER)
    Call<DrinksResult> getCallByCategory(@Query("c") String category);
    @GET(Consts.FILTER)
    Call<DrinksResult> getCallByIngredient(@Query("i") String ingredient);
    @GET(Consts.FILTER)
    Call<DrinksResult> getCallByAlcoholic(@Query("a") String alcoholic);
    @GET(Consts.FILTER)
    Call<DrinksResult> getCallByGlass(@Query("g") String glass);
    @GET(Consts.BY_ID)
    Call<DrinkResult> getDrinkById(@Query("i") String id);
}

package com.example.shahp.finalproject.MVP.Interactor;

import com.example.shahp.finalproject.Models.AlcoholicResult.AlcoholicResult;
import com.example.shahp.finalproject.Models.categoryList.CategoryResults;
import com.example.shahp.finalproject.Models.drinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.glassList.GlassResults;
import com.example.shahp.finalproject.Models.ingredientResults.IngredientResults;
import com.example.shahp.finalproject.Service.Consts;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shahp on 31/07/2017.
 */

public interface Interactor {
    Observable<CategoryResults> getCategoryList();
    Observable<GlassResults> getGlassList();
    Observable<IngredientResults> getIngredientList();
    Observable<AlcoholicResult>getAlcoholicList();
    Observable<DrinksResult> getByCategory(String category);
    Observable<DrinksResult> getByIngredient(@Query("i") String ingredient);
    Observable<DrinksResult> getByAlcoholic(@Query("a") String alcoholic);
    Observable<DrinksResult> getByGlass(@Query("g") String glass);
    Call<DrinkResult> getDrinkById(String id);
    Call<DrinksResult> getCallByIngredient(String ingredient);
    Call<DrinksResult> getCallByCategory(String category);
    Call<DrinksResult> getCallByAlcoholic( String alcoholic);
    Call<DrinksResult> getCallByGlass(String glass);

}

package com.example.shahp.finalproject.MVP.Interactor;

import com.example.shahp.finalproject.Models.AlcoholicResult.AlcoholicResult;
import com.example.shahp.finalproject.Models.CategoryList.CategoryResults;
import com.example.shahp.finalproject.Models.DrinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.GlassList.GlassResults;
import com.example.shahp.finalproject.Models.IngredientResults.IngredientResults;

import io.reactivex.Observable;
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
    Observable<DrinkResult> getDrinkById(String id);

}

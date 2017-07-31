package com.example.shahp.finalproject.Service;

import com.example.shahp.finalproject.Models.categoryList.CategoryResults;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.glassList.GlassResults;
import com.example.shahp.finalproject.Models.ingredientResults.IngredientResults;

import io.reactivex.Observable;
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
    @GET(Consts.BY_CATEOGY)
    Observable<DrinksResult> getByCategory(@Query("c") String category);

//    http://www.thecocktaildb.com/api/json/v1/1/list.php?c=list
//    http://www.thecocktaildb.com/api/json/v1/1/list.php?g=list
//    http://www.thecocktaildb.com/api/json/v1/1/list.php?i=list
//    http://www.thecocktaildb.com/api/json/v1/1/list.php?a=list
}

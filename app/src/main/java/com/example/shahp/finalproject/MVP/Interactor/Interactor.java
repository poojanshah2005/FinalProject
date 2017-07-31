package com.example.shahp.finalproject.MVP.Interactor;

import com.example.shahp.finalproject.Models.categoryList.CategoryResults;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.glassList.GlassResults;

import io.reactivex.Observable;

/**
 * Created by shahp on 31/07/2017.
 */

public interface Interactor {
    Observable<CategoryResults> getCategoryList();
    Observable<GlassResults> getGlassList();
    Observable<DrinksResult> getByCategory(String category);
}

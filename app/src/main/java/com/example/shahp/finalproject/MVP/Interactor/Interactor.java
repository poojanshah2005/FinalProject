package com.example.shahp.finalproject.MVP.Interactor;

import com.example.shahp.finalproject.Models.categoryList.CategoryList;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;

import io.reactivex.Observable;

/**
 * Created by shahp on 31/07/2017.
 */

public interface Interactor {
    Observable<CategoryList> getCategoryList();
    Observable<DrinksResult> getByCategory(String category);
}

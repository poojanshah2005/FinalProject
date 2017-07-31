package com.example.shahp.finalproject.Service;

import com.example.shahp.finalproject.Models.categoryList.CategoryList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by shahp on 31/07/2017.
 */

public interface RequestInterface {

    @GET(Consts.CategoryList)
    Observable<CategoryList> getCategoryList();
}

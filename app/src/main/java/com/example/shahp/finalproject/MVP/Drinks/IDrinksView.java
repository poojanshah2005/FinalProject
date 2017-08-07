package com.example.shahp.finalproject.MVP.Drinks;

import com.example.shahp.finalproject.MVP.MVPView;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;

/**
 * Created by shahp on 14/07/2017.
 */

/**
 * Contract for the View
 */
public interface IDrinksView extends MVPView {

    //mvp step 3

    void onFetchDataSuccess(DrinksResult drinksResult);
    void onFetchDataFailure(Throwable throwable);
    void onFetchDataCompleted();
    void onFetchDataInProgress();

}
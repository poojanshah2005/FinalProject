package com.example.shahp.finalproject.MVP.Drink;

import com.example.shahp.finalproject.MVP.MVPView;
import com.example.shahp.finalproject.Models.DrinkResult.DrinkResult;

/**
 * Created by shahp on 14/07/2017.
 */

/**
 * Contract for the View
 */
public interface IDrinkView extends MVPView {

    //mvp step 3

    void onFetchDataSuccess(DrinkResult drinkResult);
    void onFetchDataFailure(Throwable throwable);
    void onFetchDataCompleted();
    void onFetchDataInProgress();

}
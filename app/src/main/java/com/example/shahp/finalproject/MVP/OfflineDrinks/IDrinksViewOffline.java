package com.example.shahp.finalproject.MVP.OfflineDrinks;

import com.example.shahp.finalproject.MVP.MVPView;
import com.example.shahp.finalproject.Models.DrinkResult.Drink;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;

import java.util.List;

/**
 * Created by shahp on 14/07/2017.
 */

/**
 * Contract for the View
 */
public interface IDrinksViewOffline extends MVPView {

    //mvp step 3

    void onFetchDataSuccess();
    void onFetchDataFailure(Throwable throwable);
    void onFetchDataCompleted();
    void onFetchDataInProgress();

}
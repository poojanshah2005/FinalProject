package com.example.shahp.finalproject.MVP.Drink;

import com.example.shahp.finalproject.MVP.MVPPresenter;

/**
 * Created by shahp on 14/07/2017.
 */

public interface IDrinkPresenter extends MVPPresenter<IDrinkView> {
    void performDrinkDisplay(String value);
}

package com.example.shahp.finalproject.MVP.Drinks;

import com.example.shahp.finalproject.MVP.Drinks.IDrinksView;
import com.example.shahp.finalproject.MVP.MVPPresenter;

/**
 * Created by shahp on 14/07/2017.
 */

public interface IDrinksPresenter extends MVPPresenter<IDrinksView> {
    void performListDisplay(String category);
}

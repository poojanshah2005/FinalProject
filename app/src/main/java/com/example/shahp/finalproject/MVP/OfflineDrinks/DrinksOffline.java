package com.example.shahp.finalproject.MVP.OfflineDrinks;

/**
 * Created by shahp on 08/08/2017.
 */

/**
 * displaying offline drinks
 */
public class DrinksOffline implements IDrinksPresenterOffline {
    IDrinksViewOffline iDrinksViewOffline;
    @Override
    public void attachView(IDrinksViewOffline MVPView) {
        this.iDrinksViewOffline = MVPView;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void performListDisplay() {
        iDrinksViewOffline.onFetchDataSuccess();
    }
}

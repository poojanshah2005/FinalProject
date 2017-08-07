package com.example.shahp.finalproject.MVP.Drink;

import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.Models.DrinkResult.DrinkResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Poojan on 06/08/2017.
 */

public class DisplayDrink implements IDrinkPresenter {
    InteractorImpl interactor_;
    IDrinkView iDrinkView;


    public DisplayDrink(InteractorImpl interactor_) {
        this.interactor_ = interactor_;
    }

    private void OnError(Throwable throwable) {

    }

    @Override
    public void attachView(IDrinkView MVPView) {
        this.iDrinkView = MVPView;
    }

    @Override
    public void detachView() {
    }

    @Override
    public void performDrinkDisplay(String category) {
        interactor_.getDrinkById(category).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(DisplayDrink.this::onDisplayDrinkSuccess, DisplayDrink.this::OnError);

    }

    private void onDisplayDrinkSuccess(DrinkResult drinkResult) {
        iDrinkView.onFetchDataSuccess(drinkResult);
    }
}

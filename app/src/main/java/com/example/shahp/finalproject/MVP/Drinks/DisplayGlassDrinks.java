package com.example.shahp.finalproject.MVP.Drinks;

import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shahp on 01/08/2017.
 */

public class DisplayGlassDrinks implements IDrinksPresenter {
    InteractorImpl interactor_;
    IDrinksView iDrinksView;


    public DisplayGlassDrinks(InteractorImpl interactor_) {
        this.interactor_ = interactor_;
    }

    @Override
    public void performListDisplay(String category) {
        interactor_.getByGlass(category).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(DisplayGlassDrinks.this::onDisplayCategoryListSuccess, DisplayGlassDrinks.this::OnError);

    }

    private void OnError(Throwable throwable) {

    }

    private void onDisplayCategoryListSuccess(DrinksResult drinksResult) {
        iDrinksView.onFetchDataSuccess(drinksResult);
    }

    @Override
    public void attachView(IDrinksView MVPView) {
        this.iDrinksView = MVPView;
    }

    @Override
    public void detachView() {
    }
}

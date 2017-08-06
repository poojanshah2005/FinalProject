package com.example.shahp.finalproject.MVP;

import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shahp on 01/08/2017.
 */

public class DisplayIngredientDrinks implements IMusicListPresenter {
    InteractorImpl interactor_;
    IMusicListView iMusicListView;


    public DisplayIngredientDrinks(InteractorImpl interactor_) {
        this.interactor_ = interactor_;
    }

    @Override
    public void performListDisplay(String category) {
        interactor_.getByIngredient(category).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(DisplayIngredientDrinks.this::onDisplayCategoryListSuccess, DisplayIngredientDrinks.this::OnError);

    }

    private void OnError(Throwable throwable) {

    }

    private void onDisplayCategoryListSuccess(DrinksResult drinksResult) {
        iMusicListView.onFetchDataSuccess(drinksResult);
    }

    @Override
    public void attachView(IMusicListView MVPView) {
        this.iMusicListView = MVPView;
    }

    @Override
    public void detachView() {
    }
}

package com.example.shahp.finalproject.MVP;

import android.util.Log;

import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shahp on 01/08/2017.
 */

public class DisplayAlcoholicDrinks implements IMusicListPresenter {
    InteractorImpl interactor_;
    IMusicListView iMusicListView;


    public DisplayAlcoholicDrinks(InteractorImpl interactor_) {
        this.interactor_ = interactor_;
    }

    @Override
    public void performListDisplay(String category) {
        interactor_.getByAlcoholic(category).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(DisplayAlcoholicDrinks.this::onDisplayCategoryListSuccess, DisplayAlcoholicDrinks.this::OnError);

    }

    private void OnError(Throwable throwable) {
        Log.i("Messag",throwable.getMessage());

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

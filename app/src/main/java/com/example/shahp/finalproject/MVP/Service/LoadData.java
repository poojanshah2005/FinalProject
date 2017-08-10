package com.example.shahp.finalproject.MVP.Service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.shahp.finalproject.MVP.Interactor.Interactor;
import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.MainActivity;
import com.example.shahp.finalproject.Models.AlcoholicResult.Alcoholic;
import com.example.shahp.finalproject.Models.AlcoholicResult.AlcoholicResult;
import com.example.shahp.finalproject.Models.CategoryList.Category;
import com.example.shahp.finalproject.Models.CategoryList.CategoryResults;
import com.example.shahp.finalproject.Models.DrinksResult.Drink;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.GlassList.Glass;
import com.example.shahp.finalproject.Models.GlassList.GlassResults;
import com.example.shahp.finalproject.Models.IngredientResults.Ingredient;
import com.example.shahp.finalproject.Models.IngredientResults.IngredientResults;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shahp on 10/08/2017.
 */

public class LoadData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Interactor interactor_;
    int i = 0;

    public LoadData (Context context){
        this.context = context;
        interactor_ = new InteractorImpl(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if(Utils.isNetworkAvailable(this.context)){
            interactor_.getCategoryList()
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessGetCategoryList, LoadData.this::OnError);

            interactor_.getAlcoholicList()
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessGetAlcoholicList, LoadData.this::OnError);

            interactor_.getGlassList()
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessGetGlassList, LoadData.this::OnError);

            interactor_.getIngredientList()
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessGetIngredientList, LoadData.this::OnError);
        }
        return null;
    }

    private void onSuccessGetIngredientList(IngredientResults ingredientResults) {
        for(Ingredient value: ingredientResults.getIngredients()) {
            String valueString = value.getStrIngredient1();
            if(valueString.length() > 2)
            interactor_.getByIngredient(valueString)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnError);
        }
    }

    private void onSuccessGetGlassList(GlassResults glassResults) {
        for(Glass value: glassResults.getGlass()) {
            interactor_.getByGlass(value.getStrGlass())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnError);
        }
    }

    private void onSuccessGetAlcoholicList(AlcoholicResult alcoholicResult) {
        for(Alcoholic value: alcoholicResult.getAlcoholics()) {
            interactor_.getByAlcoholic(value.getStrAlcoholic())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnError);
        }
    }

    private void onSuccessGetCategoryList(CategoryResults categoryResults) {
        for(Category value: categoryResults.getCategories()) {
            interactor_.getByCategory(value.getStrCategory())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnError);
        }
    }

    private void onSuccessDrinks(DrinksResult drinksResult) {
        for(Drink drink:drinksResult.getDrinks()){
            String value = drink.getIdDrink();

        }
    }

    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnError(Throwable throwable) {
        Log.i("OnErrorLoader", throwable.getMessage());
    }
}

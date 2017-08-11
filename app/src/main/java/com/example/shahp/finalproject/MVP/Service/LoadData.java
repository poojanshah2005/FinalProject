package com.example.shahp.finalproject.MVP.Service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.shahp.finalproject.MVP.Interactor.Interactor;
import com.example.shahp.finalproject.MVP.Interactor.InteractorImpl;
import com.example.shahp.finalproject.Models.AlcoholicResult.Alcoholic;
import com.example.shahp.finalproject.Models.AlcoholicResult.AlcoholicResult;
import com.example.shahp.finalproject.Models.CategoryList.Category;
import com.example.shahp.finalproject.Models.CategoryList.CategoryResults;
import com.example.shahp.finalproject.Models.DrinkResult.DrinkResult;
import com.example.shahp.finalproject.Models.DrinksResult.Drink;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;
import com.example.shahp.finalproject.Models.GlassList.Glass;
import com.example.shahp.finalproject.Models.GlassList.GlassResults;
import com.example.shahp.finalproject.Models.IngredientResults.Ingredient;
import com.example.shahp.finalproject.Models.IngredientResults.IngredientResults;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by shahp on 10/08/2017.
 */

public class LoadData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Interactor interactor_;
    int i = 3000;
    int a = 100;
    int b = 350;

    public LoadData (Context context){
        this.context = context;
        interactor_ = new InteractorImpl(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
//        if(Utils.isNetworkAvailable(this.context)){

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getCategoryList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetCategoryList, LoadData.this::OnErrorsCategoryList);


                }
            }, i * 1);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getCategoryList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetCategoryList, LoadData.this::OnErrorsCategoryList);


                }
            }, i * 2);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getAlcoholicList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetAlcoholicList, LoadData.this::OnEngorgeAlcoholicList);


                }
            }, i * 3);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getGlassList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetGlassList, LoadData.this::OnEngorgeGlassList);


                }
            }, i * 4);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getIngredientList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetIngredientList, LoadData.this::OnEngorgeIngredientList);


                }
            }, i * 5);

//        }
        return null;
    }




    private void onSuccessGetIngredientList(IngredientResults ingredientResults) {
        for(Ingredient value: ingredientResults.getIngredients()) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                // run AsyncTask here.
                Log.i("Drink", value.getStrIngredient1());
                interactor_.getByIngredient(value.getStrIngredient1())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnEngorgeByIngredient);


            }
        },a);
        }
    }

    private void onSuccessGetGlassList(GlassResults glassResults) {
        for(Glass value: glassResults.getGlass()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getByGlass(value.getStrGlass())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnErrorSuccessGetGlassList);


                }
            },a);
        }
    }

    private void onSuccessGetAlcoholicList(AlcoholicResult alcoholicResult) {
        for(Alcoholic value: alcoholicResult.getAlcoholics()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getByAlcoholic(value.getStrAlcoholic())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnErrorSuccessGetAlcoholicList);


                }
            },a);

        }
    }

    private void onSuccessGetCategoryList(CategoryResults categoryResults) {
        for(Category value: categoryResults.getCategories()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getByCategory(value.getStrCategory())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnErrorSuccessGetCategoryList);
                }
            },a);

        }
    }

    private void onSuccessDrinks(DrinksResult drinksResult) {
        for(Drink drink:drinksResult.getDrinks()){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    // run AsyncTask here.
                    interactor_.getDrinkById(drink.getIdDrink())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessDrink, LoadData.this::OnErrorSuccessGetCategoryList);
                }
            },b);

        }
    }

    private void onSuccessDrink(DrinkResult drinkResult) {
        Log.i("onSuccessDrink", String.valueOf(drinkResult.getDrinks().size()));
    }

    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnErrorSuccessGetCategoryList(Throwable throwable) {
        Log.i("OnErrorLoader", "OnErrorSuccessGetCategoryList");
        Log.i("OnErrorLoader", throwable.getMessage());
    }

    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnErrorSuccessGetAlcoholicList(Throwable throwable) {
        Log.i("OnErrorLoader", "OnErrorSuccessGetAlcoholicList");
        Log.i("OnErrorLoader", throwable.getMessage());
    }

    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnErrorSuccessGetGlassList(Throwable throwable) {
        Log.i("OnErrorLoader", "OnErrorSuccessGetGlassList");
        Log.i("OnErrorLoader", throwable.getMessage());
    }

    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnEngorgeByIngredient(Throwable throwable) {
        Log.i("OnErrorLoader", "OnEngorgeByIngredient");
        Log.i("OnErrorLoader", throwable.getMessage());
    }


    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnEngorgeIngredientList(Throwable throwable) {
        Log.i("OnErrorLoader", "OnEngorgeIngredientList");
        Log.i("OnErrorLoader", throwable.getMessage());
    }

    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnEngorgeGlassList(Throwable throwable) {
        Log.i("OnErrorLoader", "OnEngorgeGlassList");
        Log.i("OnErrorLoader", throwable.getMessage());
    }

    /**
     * on error when database not come thought
     * @param throwable
     */
    private void OnErrorsCategoryList(Throwable throwable) {
        Log.i("OnErrorLoader", "OnErrorsCategoryList");
        Log.i("OnErrorLoader", throwable.getMessage());
    }

    private void OnEngorgeAlcoholicList(Throwable throwable) {
        Log.i("OnErrorLoader", "OnEngorgeAlcoholicList");
        Log.i("OnErrorLoader", throwable.getMessage());

    }
}

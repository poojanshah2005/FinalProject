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

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shahp on 10/08/2017.
 */

public class LoadData extends AsyncTask<Void, Void, Void> {

    private final int seconds = 1000;
    private Context context;
    private Interactor interactor_;
    public LoadData (Context context){
        this.context = context;
        interactor_ = new InteractorImpl(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if(Utils.isNetworkAvailable(this.context)){

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    interactor_.getCategoryList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetCategoryList, LoadData.this::OnErrorsCategoryList);
                }
            }, seconds * 10);// 10 seconds delay

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    interactor_.getAlcoholicList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetAlcoholicList, LoadData.this::OnEngorgeAlcoholicList);
                }
            }, seconds * 80);// 80 seconds delay

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    interactor_.getGlassList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetGlassList, LoadData.this::OnEngorgeGlassList);
                }
            }, seconds * 160);// 160 seconds delay

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    interactor_.getIngredientList()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(LoadData.this::onSuccessGetIngredientList, LoadData.this::OnEngorgeIngredientList);
                }
            }, seconds * 240);// 240 seconds delay

        }
        return null;
    }




    private void onSuccessGetIngredientList(IngredientResults ingredientResults) {

        try {
            for(Ingredient ingredient : ingredientResults.getIngredients())
                interactor_.getByIngredient(ingredient.getStrIngredient1())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnEngorgeIngredientList);
        } catch (OutOfMemoryError e){
            Log.e("Error",e.getMessage());
        } catch (UndeliverableException e){
            Log.e("Error",e.getMessage());
        } catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }

    private void onSuccessDrinkFake(DrinksResult drinksResult) {
    }


    private void onSuccessGetGlassList(GlassResults glassResults) {

        try {
            for(Glass glass: glassResults.getGlass()){
                interactor_.getByGlass(glass.getStrGlass())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnEngorgeIngredientList);

            }
        } catch (OutOfMemoryError e){
            Log.e("Error",e.getMessage());
        } catch (UndeliverableException e){
            Log.e("Error",e.getMessage());
        } catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }

    private void onSuccessGetAlcoholicList(AlcoholicResult alcoholicResult) {

        try {

            for(Alcoholic alcoholic: alcoholicResult.getAlcoholics()){
                interactor_.getByAlcoholic(alcoholic.getStrAlcoholic())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnEngorgeIngredientList);

            }

        } catch (OutOfMemoryError e){
            Log.e("Error",e.getMessage());
        } catch (UndeliverableException e){
            Log.e("Error",e.getMessage());
        } catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }

    private void onSuccessGetCategoryList(CategoryResults categoryResults) {
        try {
            for(Category value: categoryResults.getCategories()) {
                interactor_.getByCategory(value.getStrCategory())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(LoadData.this::onSuccessDrinks, LoadData.this::OnErrorSuccessGetCategoryList);
            }
        } catch (OutOfMemoryError e){
            Log.e("Error",e.getMessage());
        } catch (UndeliverableException e){
            Log.e("Error",e.getMessage());
        } catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }

    private void onSuccessDrinks(DrinksResult drinksResult) {

        try {
            for (Drink drink : drinksResult.getDrinks()) {
                Log.i("Drink154", drink.getStrDrink());
                interactor_.getDrinkById(drink.getIdDrink())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(LoadData.this::onSuccessDrink, LoadData.this::OnErrorSuccessGetCategoryList);
            }
        } catch (OutOfMemoryError e){
            Log.e("Error",e.getMessage());
        } catch (UndeliverableException e){
            Log.e("Error",e.getMessage());
        } catch (Exception e){
            Log.e("Error",e.getMessage());
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

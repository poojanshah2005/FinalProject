package com.example.shahp.finalproject.MVP.Service.Realm;

import com.example.shahp.finalproject.Models.DrinkResult.Drink;

import java.util.List;

import io.realm.Realm;

/**
 * Created by shahp on 07/07/2017.
 */

/**
 * realm Helper, entity manger like class
 */
public class RealmHelper {
    Realm realm;

    /**
     * init RealmHelper
     * @param realm
     */
    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    /**
     * save drink
     * @param drink
     */
    public void saveData(final com.example.shahp.finalproject.Models.DrinkResult.Drink drink){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(drink);
            }
        });
    }

    /**
     * get Drink
     * @param idDrink
     * @return
     */
    public com.example.shahp.finalproject.Models.DrinkResult.Drink getDrink(String idDrink){
         return realm.where(Drink.class).equalTo("idDrink",idDrink).findFirst();
    }

    /**
     * get all drinks saved.
     * @return
     */
    public List<Drink> getDrinks() {
        return realm.where(Drink.class).findAll();
    }

    /**
     * check if object is in realm.
     */
    public boolean containsDrink(String idDrink){
        Drink drink = realm.where(Drink.class).contains("idDrink",idDrink).findFirst();
        if(drink == null) {
            return false;
        }
        else if (drink.isValid()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * remove drink from realm
     * @param idDrink
     */
    public void removeDrink(String idDrink){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Drink.class).contains("idDrink",idDrink).findFirst().deleteFromRealm();
            }
        });

    }
}

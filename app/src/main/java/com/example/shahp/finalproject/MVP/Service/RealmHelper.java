package com.example.shahp.finalproject.MVP.Service;

import com.example.shahp.finalproject.Models.DrinkResult.Drink;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by shahp on 07/07/2017.
 */

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveData(final com.example.shahp.finalproject.Models.DrinkResult.Drink drink){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(drink);
            }
        });
    }

    public com.example.shahp.finalproject.Models.DrinkResult.Drink getDrink(String idDrink){
         return realm.where(Drink.class).equalTo("idDrink",idDrink).findFirst();
    }

    public com.example.shahp.finalproject.Models.DrinksResult.Drink getDrinkBasic(String idDrink){
        com.example.shahp.finalproject.Models.DrinksResult.Drink drink;
        drink = new com.example.shahp.finalproject.Models.DrinksResult.Drink();
        com.example.shahp.finalproject.Models.DrinkResult.Drink resultDrink;
        resultDrink = getDrink(idDrink);
        drink.setStrDrink(resultDrink.getStrDrink());
        drink.setIdDrink(resultDrink.getIdDrink());
        drink.setStrDrinkThumb(resultDrink.getStrDrinkThumb());
        return drink;
    }

    public List<Drink> getDrinks() {
        return realm.where(Drink.class).findAll();
    }


//    public ArrayList<String> getCustomers(){
//        ArrayList<String> customers = new ArrayList<>();
//        RealmResults<CustomerModel> c = realm.where(CustomerModel.class).findAll();
//        for(CustomerModel customerModel: c){
//            customers.add(customerModel.getName());
//            customers.add(customerModel.getEmail());
//        }
//        return customers;
//    }
}

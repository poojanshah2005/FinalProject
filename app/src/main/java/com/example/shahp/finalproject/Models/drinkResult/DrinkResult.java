package com.example.shahp.finalproject.Models.drinkResult;

/**
 * Created by shahp on 03/08/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrinkResult implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;
    public final static Parcelable.Creator<DrinkResult> CREATOR = new Creator<DrinkResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DrinkResult createFromParcel(Parcel in) {
            DrinkResult instance = new DrinkResult();
            in.readList(instance.drinks, (com.example.shahp.finalproject.Models.drinkResult.Drink.class.getClassLoader()));
            return instance;
        }

        public DrinkResult[] newArray(int size) {
            return (new DrinkResult[size]);
        }

    }
            ;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(drinks);
    }

    public int describeContents() {
        return 0;
    }

}

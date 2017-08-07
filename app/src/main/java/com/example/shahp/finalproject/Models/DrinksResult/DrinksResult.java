package com.example.shahp.finalproject.Models.DrinksResult;

/**
 * Created by shahp on 31/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrinksResult implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;
    public final static Parcelable.Creator<DrinksResult> CREATOR = new Creator<DrinksResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DrinksResult createFromParcel(Parcel in) {
            DrinksResult instance = new DrinksResult();
            in.readList(instance.drinks, (com.example.shahp.finalproject.Models.DrinksResult.Drink.class.getClassLoader()));
            return instance;
        }

        public DrinksResult[] newArray(int size) {
            return (new DrinksResult[size]);
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

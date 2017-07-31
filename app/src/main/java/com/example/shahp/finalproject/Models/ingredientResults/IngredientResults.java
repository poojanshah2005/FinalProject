package com.example.shahp.finalproject.Models.ingredientResults;

/**
 * Created by shahp on 31/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IngredientResults implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;
    public final static Parcelable.Creator<IngredientResults> CREATOR = new Creator<IngredientResults>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IngredientResults createFromParcel(Parcel in) {
            IngredientResults instance = new IngredientResults();
            in.readList(instance.drinks, (com.example.shahp.finalproject.Models.ingredientResults.Drink.class.getClassLoader()));
            return instance;
        }

        public IngredientResults[] newArray(int size) {
            return (new IngredientResults[size]);
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

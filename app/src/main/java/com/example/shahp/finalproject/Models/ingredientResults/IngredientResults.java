package com.example.shahp.finalproject.Models.ingredientResults;

/**
 * Created by shahp on 31/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IngredientResults implements Parcelable
{

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @SerializedName("drinks")
    @Expose
    private List<Ingredient> ingredients = null;
    public final static Parcelable.Creator<IngredientResults> CREATOR = new Creator<IngredientResults>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IngredientResults createFromParcel(Parcel in) {
            IngredientResults instance = new IngredientResults();
            in.readList(instance.ingredients, (Ingredient.class.getClassLoader()));
            return instance;
        }

        public IngredientResults[] newArray(int size) {
            return (new IngredientResults[size]);
        }

    }
            ;
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(ingredients);
    }

    public int describeContents() {
        return 0;
    }

}

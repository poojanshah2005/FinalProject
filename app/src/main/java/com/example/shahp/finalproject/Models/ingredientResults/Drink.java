package com.example.shahp.finalproject.Models.ingredientResults;

/**
 * Created by shahp on 31/07/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drink implements Parcelable
{

    @SerializedName("strIngredient1")
    @Expose
    private String strIngredient1;
    public final static Parcelable.Creator<Drink> CREATOR = new Creator<Drink>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Drink createFromParcel(Parcel in) {
            Drink instance = new Drink();
            instance.strIngredient1 = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Drink[] newArray(int size) {
            return (new Drink[size]);
        }

    }
            ;

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(strIngredient1);
    }

    public int describeContents() {
        return 0;
    }

}

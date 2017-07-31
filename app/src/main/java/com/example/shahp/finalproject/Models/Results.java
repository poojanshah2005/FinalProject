package com.example.shahp.finalproject.Models;

/**
 * Created by shahp on 31/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;
    public final static Parcelable.Creator<Results> CREATOR = new Creator<Results>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Results createFromParcel(Parcel in) {
            Results instance = new Results();
            in.readList(instance.drinks, (com.example.shahp.finalproject.Models.Drink.class.getClassLoader()));
            return instance;
        }

        public Results[] newArray(int size) {
            return (new Results[size]);
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

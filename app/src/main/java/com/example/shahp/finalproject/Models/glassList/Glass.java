package com.example.shahp.finalproject.Models.glassList;

/**
 * Created by shahp on 31/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Glass implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;
    public final static Parcelable.Creator<Glass> CREATOR = new Creator<Glass>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Glass createFromParcel(Parcel in) {
            Glass instance = new Glass();
            in.readList(instance.drinks, (com.example.shahp.finalproject.Models.glassList.Drink.class.getClassLoader()));
            return instance;
        }

        public Glass[] newArray(int size) {
            return (new Glass[size]);
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
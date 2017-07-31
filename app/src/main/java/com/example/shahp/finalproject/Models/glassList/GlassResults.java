package com.example.shahp.finalproject.Models.glassList;

/**
 * Created by shahp on 31/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlassResults implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Glass> drinks = null;
    public final static Parcelable.Creator<GlassResults> CREATOR = new Creator<GlassResults>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GlassResults createFromParcel(Parcel in) {
            GlassResults instance = new GlassResults();
            in.readList(instance.drinks, (Glass.class.getClassLoader()));
            return instance;
        }

        public GlassResults[] newArray(int size) {
            return (new GlassResults[size]);
        }

    }
            ;

    public List<Glass> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Glass> drinks) {
        this.drinks = drinks;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(drinks);
    }

    public int describeContents() {
        return 0;
    }

}
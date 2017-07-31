package com.example.shahp.finalproject.Models.drinksResult;

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

    @SerializedName("strDrink")
    @Expose
    private String strDrink;
    @SerializedName("strDrinkThumb")
    @Expose
    private Object strDrinkThumb;
    @SerializedName("idDrink")
    @Expose
    private String idDrink;
    public final static Parcelable.Creator<Drink> CREATOR = new Creator<Drink>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Drink createFromParcel(Parcel in) {
            Drink instance = new Drink();
            instance.strDrink = ((String) in.readValue((String.class.getClassLoader())));
            instance.strDrinkThumb = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.idDrink = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Drink[] newArray(int size) {
            return (new Drink[size]);
        }

    }
            ;

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public Object getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(Object strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(strDrink);
        dest.writeValue(strDrinkThumb);
        dest.writeValue(idDrink);
    }

    public int describeContents() {
        return 0;
    }

}
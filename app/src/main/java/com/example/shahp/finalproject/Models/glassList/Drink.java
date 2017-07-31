package com.example.shahp.finalproject.Models.glassList;

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

    @SerializedName("strGlass")
    @Expose
    private String strGlass;
    public final static Parcelable.Creator<Drink> CREATOR = new Creator<Drink>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Drink createFromParcel(Parcel in) {
            Drink instance = new Drink();
            instance.strGlass = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Drink[] newArray(int size) {
            return (new Drink[size]);
        }

    }
            ;

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(strGlass);
    }

    public int describeContents() {
        return 0;
    }

}

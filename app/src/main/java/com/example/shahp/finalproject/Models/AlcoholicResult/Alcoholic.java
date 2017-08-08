package com.example.shahp.finalproject.Models.AlcoholicResult;

/**
 * Created by Poojan on 05/08/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Alcoholic is a data type that the user can filter drink
 */
public class Alcoholic implements Parcelable
{

    @SerializedName("strAlcoholic")
    @Expose
    private String strAlcoholic;
    public final static Parcelable.Creator<Alcoholic> CREATOR = new Creator<Alcoholic>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Alcoholic createFromParcel(Parcel in) {
            Alcoholic instance = new Alcoholic();
            instance.strAlcoholic = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Alcoholic[] newArray(int size) {
            return (new Alcoholic[size]);
        }

    }
            ;

    public String getStrAlcoholic() {
        return strAlcoholic;
    }

    public void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(strAlcoholic);
    }

    public int describeContents() {
        return 0;
    }

}

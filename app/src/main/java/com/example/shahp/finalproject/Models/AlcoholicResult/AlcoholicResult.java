package com.example.shahp.finalproject.Models.AlcoholicResult;

/**
 * Created by Poojan on 05/08/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * used to get list of Alcoholic data types
 */
public class AlcoholicResult implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Alcoholic> alcoholics = null;
    public final static Parcelable.Creator<AlcoholicResult> CREATOR = new Creator<AlcoholicResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AlcoholicResult createFromParcel(Parcel in) {
            AlcoholicResult instance = new AlcoholicResult();
            in.readList(instance.alcoholics, (Alcoholic.class.getClassLoader()));
            return instance;
        }

        public AlcoholicResult[] newArray(int size) {
            return (new AlcoholicResult[size]);
        }

    }
            ;

    public List<Alcoholic> getAlcoholics() {
        return alcoholics;
    }

    public void setAlcoholics(List<Alcoholic> alcoholics) {
        this.alcoholics = alcoholics;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(alcoholics);
    }

    public int describeContents() {
        return 0;
    }

}

package com.example.shahp.finalproject.Models.GlassList;

/**
 * Created by shahp on 31/07/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Glass implements Parcelable
{

    @SerializedName("strGlass")
    @Expose
    private String strGlass;
    public final static Parcelable.Creator<Glass> CREATOR = new Creator<Glass>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Glass createFromParcel(Parcel in) {
            Glass instance = new Glass();
            instance.strGlass = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Glass[] newArray(int size) {
            return (new Glass[size]);
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

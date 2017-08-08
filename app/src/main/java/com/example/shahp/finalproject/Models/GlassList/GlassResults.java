package com.example.shahp.finalproject.Models.GlassList;

/**
 * Created by shahp on 31/07/2017.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * is used to get a glass types, which the user can use to filter
 */
public class GlassResults implements Parcelable
{

    @SerializedName("drinks")
    @Expose
    private List<Glass> glass = null;
    public final static Parcelable.Creator<GlassResults> CREATOR = new Creator<GlassResults>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GlassResults createFromParcel(Parcel in) {
            GlassResults instance = new GlassResults();
            in.readList(instance.glass, (Glass.class.getClassLoader()));
            return instance;
        }

        public GlassResults[] newArray(int size) {
            return (new GlassResults[size]);
        }

    }
            ;

    public List<Glass> getGlass() {
        return glass;
    }

    public void setGlass(List<Glass> glass) {
        this.glass = glass;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(glass);
    }

    public int describeContents() {
        return 0;
    }

}
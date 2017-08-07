package com.example.shahp.finalproject.Models.CategoryList;

/**
 * Created by shahp on 31/07/2017.
 */

import android.os.Parcel;
        import android.os.Parcelable;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable
{

    @SerializedName("strCategory")
    @Expose
    private String strCategory;
    public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            Category instance = new Category();
            instance.strCategory = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    }
            ;

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(strCategory);
    }

    public int describeContents() {
        return 0;
    }

}
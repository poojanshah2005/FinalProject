package com.example.shahp.finalproject.Models.categoryList;

/**
 * Created by shahp on 31/07/2017.
 */


        import java.util.List;
        import android.os.Parcel;
        import android.os.Parcelable;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class CategoryResults implements Parcelable
{

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @SerializedName("drinks")
    @Expose
    private List<Category> categories = null;
    public final static Parcelable.Creator<CategoryResults> CREATOR = new Creator<CategoryResults>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryResults createFromParcel(Parcel in) {
            CategoryResults instance = new CategoryResults();
            in.readList(instance.categories, (com.example.shahp.finalproject.Models.categoryList.Category.class.getClassLoader()));
            return instance;
        }

        public CategoryResults[] newArray(int size) {
            return (new CategoryResults[size]);
        }

    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(categories);
    }

    public int describeContents() {
        return 0;
    }

}
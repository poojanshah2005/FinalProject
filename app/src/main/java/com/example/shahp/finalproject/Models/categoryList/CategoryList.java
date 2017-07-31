package com.example.shahp.finalproject.Models.categoryList;

/**
 * Created by shahp on 31/07/2017.
 */


        import java.util.List;
        import android.os.Parcel;
        import android.os.Parcelable;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class CategoryList implements Parcelable
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
    public final static Parcelable.Creator<CategoryList> CREATOR = new Creator<CategoryList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryList createFromParcel(Parcel in) {
            CategoryList instance = new CategoryList();
            in.readList(instance.categories, (com.example.shahp.finalproject.Models.categoryList.Category.class.getClassLoader()));
            return instance;
        }

        public CategoryList[] newArray(int size) {
            return (new CategoryList[size]);
        }

    }
            ;

    public List<Category> getDrinks() {
        return categories;
    }

    public void setDrinks(List<Category> drinks) {
        this.categories = drinks;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(categories);
    }

    public int describeContents() {
        return 0;
    }

}
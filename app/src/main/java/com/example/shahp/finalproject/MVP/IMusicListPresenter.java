package com.example.shahp.finalproject.MVP;

/**
 * Created by shahp on 14/07/2017.
 */

public interface IMusicListPresenter extends MVPPresenter<IMusicListView> {
    void performListDisplay(String category);
}

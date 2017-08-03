package com.example.shahp.finalproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shahp.finalproject.Models.drinksResult.Drink;
import com.example.shahp.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DisplayDrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDrinkFragment extends Fragment {
    com.example.shahp.finalproject.Models.drinkResult.Drink drink;

    public DisplayDrinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DisplayDrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayDrinkFragment newInstance() {
        DisplayDrinkFragment fragment = new DisplayDrinkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle b = getArguments();
        drink = b.getParcelable("drink");
        Log.i("drink/Fragment",drink.getStrDrink());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_drink, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

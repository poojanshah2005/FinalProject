package com.example.shahp.finalproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.shahp.finalproject.Models.drinksResult.Drink;
import com.example.shahp.finalproject.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DisplayDrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDrinkFragment extends Fragment {
    com.example.shahp.finalproject.Models.drinkResult.Drink drink;
    ImageView imDrink;
    TextView tvDrinkName, tvInstructions;
    TableLayout DrinkIngs;
    TextView tvGlass;
    TextView tvCatergories;
    TextView tvAlc;


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imDrink = (ImageView) view.findViewById(R.id.imDrink);
        tvDrinkName = (TextView) view.findViewById(R.id.tvDrinkName);
        DrinkIngs = (TableLayout) view.findViewById(R.id.displayLinear);
        tvGlass = (TextView) view.findViewById(R.id.tvGlass);
        tvCatergories = (TextView) view.findViewById(R.id.tvCatergories);
        tvAlc = (TextView) view.findViewById(R.id.tvAlc);
        tvInstructions = (TextView) view.findViewById(R.id.tvInstructions);
        //        imDrink
        tvDrinkName.setText("Drink Name: "+ drink.getStrDrink());
//        DrinkIngs
        tvGlass.setText("Glass: " + drink.getStrGlass());
        tvCatergories.setText("Category: " +drink.getStrCategory());
        tvAlc.setText("Alcoholic: " + drink.getStrAlcoholic());
        tvInstructions.setText("Instructions: " + drink.getStrInstructions());


        for (int i = 0; i <2; i++) {

            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView tv1 = new TextView(getContext());
            TextView tv2 = new TextView(getContext());
            tv1.setText(drink.getStrIngredient1());
            tv2.setText(drink.getStrMeasure1());
            row.addView(tv1);
            row.addView(tv2);
            DrinkIngs.addView(row,i);
        }


















        try {
            String imageLink = (String) drink.getStrDrinkThumb();
            if(imageLink != null) {
                Log.i("onBindViewHolder", imageLink);
                Picasso
                        .with(getContext())
                        .load(imageLink)
                        .into(imDrink);
            }
        }
        catch(Exception e){
            Log.i("Error", e.getMessage());
            Log.i("Error", String.valueOf(e.getCause()));
        }
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

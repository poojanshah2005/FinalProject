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

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            TableRow row1= new TableRow(getContext());
            TableRow row2= new TableRow(getContext());
            TableRow row3= new TableRow(getContext());
            TableRow row4= new TableRow(getContext());
            TableRow row5= new TableRow(getContext());
            TableRow row6= new TableRow(getContext());
            TableRow row7= new TableRow(getContext());
            TableRow row8= new TableRow(getContext());
            TableRow row9= new TableRow(getContext());
            TableRow row10= new TableRow(getContext());
            TableRow row11= new TableRow(getContext());
            TableRow row12= new TableRow(getContext());
            TableRow row13= new TableRow(getContext());
            TableRow row14= new TableRow(getContext());
            TableRow row15= new TableRow(getContext());


            row1.setLayoutParams(lp);
            row2.setLayoutParams(lp);
            row3.setLayoutParams(lp);
            row4.setLayoutParams(lp);
            row5.setLayoutParams(lp);
            row6.setLayoutParams(lp);
            row7.setLayoutParams(lp);
            row8.setLayoutParams(lp);
            row9.setLayoutParams(lp);
            row10.setLayoutParams(lp);
            row11.setLayoutParams(lp);
            row12.setLayoutParams(lp);
            row13.setLayoutParams(lp);
            row14.setLayoutParams(lp);
            row15.setLayoutParams(lp);


            TextView tv1I = new TextView(getContext());
            TextView tv1Q = new TextView(getContext());
            TextView tv2I = new TextView(getContext());
            TextView tv2Q = new TextView(getContext());
            TextView tv3I = new TextView(getContext());
            TextView tv3Q = new TextView(getContext());
            TextView tv4I = new TextView(getContext());
            TextView tv4Q = new TextView(getContext());
            TextView tv5I = new TextView(getContext());
            TextView tv5Q = new TextView(getContext());
            TextView tv6I = new TextView(getContext());
            TextView tv6Q = new TextView(getContext());
            TextView tv7I = new TextView(getContext());
            TextView tv7Q = new TextView(getContext());
            TextView tv8I = new TextView(getContext());
            TextView tv8Q = new TextView(getContext());
            TextView tv9I = new TextView(getContext());
            TextView tv9Q = new TextView(getContext());
            TextView tv10I = new TextView(getContext());
            TextView tv10Q = new TextView(getContext());
            TextView tv11I = new TextView(getContext());
            TextView tv11Q = new TextView(getContext());
            TextView tv12I = new TextView(getContext());
            TextView tv12Q = new TextView(getContext());
            TextView tv13I = new TextView(getContext());
            TextView tv13Q = new TextView(getContext());
            TextView tv14I = new TextView(getContext());
            TextView tv14Q = new TextView(getContext());
            TextView tv15I = new TextView(getContext());
            TextView tv15Q = new TextView(getContext());

            tv1I.setText(drink.getStrIngredient1());
            tv1Q.setText(drink.getStrMeasure1());

            tv2I.setText(drink.getStrIngredient2());
            tv2Q.setText(drink.getStrMeasure2());

            tv3I.setText(drink.getStrIngredient3());
            tv3Q.setText(drink.getStrMeasure3());

            tv4I.setText(drink.getStrIngredient4());
            tv4Q.setText(drink.getStrMeasure4());

            tv5I.setText(drink.getStrIngredient5());
            tv5Q.setText(drink.getStrMeasure5());

            tv6I.setText(drink.getStrIngredient6());
            tv6Q.setText(drink.getStrMeasure6());

            tv7I.setText(drink.getStrIngredient7());
            tv7Q.setText(drink.getStrMeasure7());

            tv8I.setText(drink.getStrIngredient8());
            tv8Q.setText(drink.getStrMeasure8());

            tv9I.setText(drink.getStrIngredient9());
            tv9Q.setText(drink.getStrMeasure9());

            tv10I.setText(drink.getStrIngredient10());
            tv10Q.setText(drink.getStrMeasure10());

            tv11I.setText(drink.getStrIngredient11());
            tv11Q.setText(drink.getStrMeasure11());

            tv12I.setText(drink.getStrIngredient12());
            tv12Q.setText(drink.getStrMeasure12());

            tv13I.setText(drink.getStrIngredient13());
            tv13Q.setText(drink.getStrMeasure13());

            tv14I.setText(drink.getStrIngredient14());
            tv14Q.setText(drink.getStrMeasure14());

            tv15I.setText(drink.getStrIngredient15());
            tv15Q.setText(drink.getStrMeasure15());


            row1.addView(tv1I);
            row1.addView(tv1Q);

            row2.addView(tv2I);
            row2.addView(tv2Q);

            row3.addView(tv3I);
            row3.addView(tv3Q);

            row4.addView(tv4I);
            row4.addView(tv4Q);

            row5.addView(tv5I);
            row5.addView(tv5Q);

            row6.addView(tv6I);
            row6.addView(tv6Q);

            row7.addView(tv7I);
            row7.addView(tv7Q);

            row8.addView(tv8I);
            row8.addView(tv8Q);

            row9.addView(tv9I);
            row9.addView(tv9Q);

            row10.addView(tv10I);
            row10.addView(tv10Q);

            row11.addView(tv11I);
            row11.addView(tv11Q);

            row12.addView(tv12I);
            row12.addView(tv12Q);

            row13.addView(tv13I);
            row13.addView(tv13Q);

            row14.addView(tv14I);
            row14.addView(tv14Q);

            row15.addView(tv15I);
            row15.addView(tv15Q);

            DrinkIngs.addView(row1);
            DrinkIngs.addView(row2);
            DrinkIngs.addView(row3);
            DrinkIngs.addView(row4);
            DrinkIngs.addView(row5);
            DrinkIngs.addView(row6);
            DrinkIngs.addView(row7);
            DrinkIngs.addView(row8);
            DrinkIngs.addView(row9);
            DrinkIngs.addView(row10);
            DrinkIngs.addView(row11);
            DrinkIngs.addView(row12);
            DrinkIngs.addView(row13);
            DrinkIngs.addView(row14);
            DrinkIngs.addView(row15);

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

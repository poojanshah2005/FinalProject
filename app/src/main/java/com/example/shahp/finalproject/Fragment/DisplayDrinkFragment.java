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
    int a = 35;


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






        addRow(lp,drink.getStrIngredient1(), drink.getStrMeasure1());
        addRow(lp,drink.getStrIngredient2(), drink.getStrMeasure2());
        addRow(lp,drink.getStrIngredient3(), drink.getStrMeasure3());
        addRow(lp,drink.getStrIngredient4(), drink.getStrMeasure4());
        addRow(lp,drink.getStrIngredient5(), drink.getStrMeasure5());
        addRow(lp,drink.getStrIngredient6(), drink.getStrMeasure6());
        addRow(lp,drink.getStrIngredient7(), drink.getStrMeasure7());
        addRow(lp,drink.getStrIngredient8(), drink.getStrMeasure8());
        addRow(lp,drink.getStrIngredient9(), drink.getStrMeasure9());
        addRow(lp,drink.getStrIngredient10(), drink.getStrMeasure10());
        addRow(lp,drink.getStrIngredient11(), drink.getStrMeasure11());
        addRow(lp,drink.getStrIngredient12(), drink.getStrMeasure12());
        addRow(lp,drink.getStrIngredient13(), drink.getStrMeasure13());
        addRow(lp,drink.getStrIngredient14(), drink.getStrMeasure14());
        addRow(lp,drink.getStrIngredient15(), drink.getStrMeasure15());


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

    private void addRow(TableRow.LayoutParams lp, String s1, String s2) {
        if(s1.length()> 1) {
            TextView tv1I = new TextView(getContext());
            TextView tv1Q = new TextView(getContext());
            tv1I.setText(s1);
            tv1Q.setText(s2);
            TableRow row1 = new TableRow(getContext());
            row1.setLayoutParams(lp);
            row1.setMinimumHeight(a);
            row1.addView(tv1I);
            row1.addView(tv1Q);
            DrinkIngs.addView(row1);
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

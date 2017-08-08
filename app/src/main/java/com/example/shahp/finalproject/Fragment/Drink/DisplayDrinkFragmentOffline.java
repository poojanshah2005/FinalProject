package com.example.shahp.finalproject.Fragment.Drink;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shahp.finalproject.MVP.Service.RealmHelper;
import com.example.shahp.finalproject.MainActivity;
import com.example.shahp.finalproject.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DisplayDrinkFragmentOffline#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDrinkFragmentOffline extends Fragment {
    com.example.shahp.finalproject.Models.DrinkResult.Drink drink;
    ImageView imDrink;
    TextView tvDrinkName, tvInstructions, tvGlass, tvCatergories, tvAlc;
    TableLayout DrinkIngs;
    Button saveButton;
    Realm realm;
    RealmHelper realmHelper;
    int a = 70;
    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);


    public DisplayDrinkFragmentOffline() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DisplayDrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayDrinkFragmentOffline newInstance() {
        DisplayDrinkFragmentOffline fragment = new DisplayDrinkFragmentOffline();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        Bundle b = getArguments();
        String id = b.getString("drink");
        Realm realm = Realm.getDefaultInstance();
        RealmHelper realmHelper = new RealmHelper(realm);
        drink = realmHelper.getDrink(id);
        Log.i("drink/Fragment",drink.getStrDrink());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imDrink =  view.findViewById(R.id.imDrink);
        tvDrinkName =  view.findViewById(R.id.tvDrinkName);
        DrinkIngs = view.findViewById(R.id.displayLinear);
        tvGlass = view.findViewById(R.id.tvGlass);
        tvCatergories =  view.findViewById(R.id.tvCatergories);
        tvAlc =  view.findViewById(R.id.tvAlc);
        tvInstructions =  view.findViewById(R.id.tvInstructions);
        saveButton = view.findViewById(R.id.btnSave);


        tvDrinkName.setText("Drink Name: "+ drink.getStrDrink());
        tvGlass.setText(Html.fromHtml(getString(R.string.glassTitle) +  "<u> " + drink.getStrGlass() + "</u>"));
        tvCatergories.setText(Html.fromHtml(getString(R.string.category) + "<u> " +drink.getStrCategory() + "</u>"));
        tvAlc.setText(Html.fromHtml(getString(R.string.Alcoholic) + "<u> " + drink.getStrAlcoholic() + "</u>"));
        tvInstructions.setText("Instructions: " + drink.getStrInstructions());

        tvGlass.setOnClickListener(view13 -> MainActivity.displayDrinkByGlass(drink.getStrGlass()));

        tvCatergories.setOnClickListener(view12 -> MainActivity.displayDrinkByCategory(drink.getStrCategory()));

        tvAlc.setOnClickListener(view1 -> MainActivity.displayDrinkByAlcoholic(drink.getStrAlcoholic()));

        saveButton.setOnClickListener(view14 -> {
            realmHelper.saveData(drink);
            Toast.makeText(getContext(),"Drink has been saved to the database.",Toast.LENGTH_SHORT).show();
        });

        initRows();

        try {
            String imageLink = (String) drink.getStrDrinkThumb();
            if(imageLink != null) {
                Log.i("onBindViewHolder", imageLink);
                Picasso
                        .with(getContext())
                        .load(imageLink)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(imDrink, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(getActivity())
                                        .load(imageLink)
                                        .into(imDrink, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.v("Picasso","Could not fetch image");
                                            }
                                        });
                            }
                        });
            }
        }
        catch(Exception e){
            Log.i("Error", e.getMessage());
            Log.i("Error", String.valueOf(e.getCause()));
        }
    }

    private void initRows() {
        addRow(drink.getStrIngredient1(), drink.getStrMeasure1());
        addRow(drink.getStrIngredient2(), drink.getStrMeasure2());
        addRow(drink.getStrIngredient3(), drink.getStrMeasure3());
        addRow(drink.getStrIngredient4(), drink.getStrMeasure4());
        addRow(drink.getStrIngredient5(), drink.getStrMeasure5());
        addRow(drink.getStrIngredient6(), drink.getStrMeasure6());
        addRow(drink.getStrIngredient7(), drink.getStrMeasure7());
        addRow(drink.getStrIngredient8(), drink.getStrMeasure8());
        addRow(drink.getStrIngredient9(), drink.getStrMeasure9());
        addRow(drink.getStrIngredient10(), drink.getStrMeasure10());
        addRow(drink.getStrIngredient11(), drink.getStrMeasure11());
        addRow(drink.getStrIngredient12(), drink.getStrMeasure12());
        addRow(drink.getStrIngredient13(), drink.getStrMeasure13());
        addRow(drink.getStrIngredient14(), drink.getStrMeasure14());
        addRow(drink.getStrIngredient15(), drink.getStrMeasure15());
    }

    private void addRow(String ingredient, String measurement) {
        if(ingredient.length()> 1) {
            TableRow row = new TableRow(getContext());
            TextView tv1I = new TextView(getContext());
            TextView tv1Q = new TextView(getContext());
            tv1I.setText(ingredient);
            tv1I.setPaintFlags(tv1I.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            if (measurement.length() > 1) tv1Q.setText(measurement);
            row.setLayoutParams(lp);
            row.setMinimumHeight(a);
            row.setGravity(LinearLayout.VERTICAL);
            row.addView(tv1I);
            row.addView(tv1Q);
            DrinkIngs.addView(row);
            row.setOnClickListener(view -> {
                TableRow t = (TableRow) view;
                TextView firstTextView = (TextView) t.getChildAt(0);
                String Ingredient = firstTextView.getText().toString();
                MainActivity.displayDrinkByIngredient(Ingredient);
            });
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

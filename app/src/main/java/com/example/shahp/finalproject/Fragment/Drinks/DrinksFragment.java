package com.example.shahp.finalproject.Fragment.Drinks;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shahp.finalproject.Adapters.DrinkAdapter;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;
import com.example.shahp.finalproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Displaying all drinks based on the users filtering
 */
public class DrinksFragment extends Fragment {
    //initialzing values to be used
    @BindView(R.id.fragmentDrinksRecycleView)
    RecyclerView mRecyclerView;
    DrinksResult drinksResult;

    public DrinksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DrinksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrinksFragment newInstance() {
        DrinksFragment fragment = new DrinksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * getting list of drinks to be shown
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle b = getArguments();
        this.drinksResult = b.getParcelable("drinksResult");
//        for (Drink drinkResult : drinksResult.getDrinks()) {
//            Log.i("drinkResult 65", drinkResult.getStrDrink());
//        }
    }

    /**
     * Inflate the layout for this fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_drinks, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    /**
     * init the Recycle View
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(new DrinkAdapter(drinksResult,getContext()));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }
}

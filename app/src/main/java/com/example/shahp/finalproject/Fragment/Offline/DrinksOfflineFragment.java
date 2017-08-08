package com.example.shahp.finalproject.Fragment.Offline;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shahp.finalproject.Adapters.Offline.DrinkOfflineAdapter;
import com.example.shahp.finalproject.MVP.Service.Realm.RealmHelper;
import com.example.shahp.finalproject.Models.DrinkResult.Drink;
import com.example.shahp.finalproject.R;

import java.util.List;

import io.realm.Realm;

/**
 * Displaying offline drinks
 */
public class DrinksOfflineFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    List<Drink> drinks;

    public DrinksOfflineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DrinksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrinksOfflineFragment newInstance() {
        DrinksOfflineFragment fragment = new DrinksOfflineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * getting list of drinks to be shown form realm
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Realm realm  = Realm.getDefaultInstance();
        RealmHelper realmHelper = new RealmHelper(realm);
        drinks = realmHelper.getDrinks();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks, container, false);
    }

    /**
     * init the Recycle View
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentDrinksRecycleView);
        mRecyclerView.setAdapter(new DrinkOfflineAdapter(drinks,getContext()));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }
}

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
import com.example.shahp.finalproject.Adapters.DrinkOfflineAdapter;
import com.example.shahp.finalproject.MVP.Service.RealmHelper;
import com.example.shahp.finalproject.Models.DrinkResult.Drink;
import com.example.shahp.finalproject.Models.DrinksResult.DrinksResult;
import com.example.shahp.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrinksOfflineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrinksOfflineFragment#newInstance} factory method to
 * create an instance of this fragment.
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Realm realm  = Realm.getDefaultInstance();
        RealmHelper realmHelper = new RealmHelper(realm);
        drinks = realmHelper.getDrinks();
        //        for (Drink drinkResult : drinksResult.getDrinks()) {
//            Log.i("drinkResult 65", drinkResult.getStrDrink());
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentDrinksRecycleView);
        mRecyclerView.setAdapter(new DrinkOfflineAdapter(drinks,getContext()));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

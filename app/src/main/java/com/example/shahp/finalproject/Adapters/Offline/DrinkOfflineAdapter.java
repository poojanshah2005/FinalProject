package com.example.shahp.finalproject.Adapters.Offline;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shahp.finalproject.MainActivity;
import com.example.shahp.finalproject.Models.DrinkResult.Drink;
import com.example.shahp.finalproject.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shahp on 02/08/2017.
 */

/**
 * this adapter is used when viewing offline drinks
 */
public class DrinkOfflineAdapter extends RecyclerView.Adapter<DrinkOfflineAdapter.MyViewHolder> {
    List<Drink> drinks;
    Context context;
    public DrinkOfflineAdapter(List<Drink> drinks, Context context) {
        this.drinks = drinks;
        this.context = context;
    }

    /**
     * inflating the view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_card,parent,false);
        return new MyViewHolder(v);
    }

    /**
     * binding each instance of a drink to a card
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String drinkName = drinks.get(position).getStrDrink();

        holder.tvDrink.setText(drinkName);
        try {
            String imageLink = (String) drinks.get(position).getStrDrinkThumb();
            if(imageLink != null) {
//                Log.i("onBindViewHolder", imageLink);
                Picasso
                        .with(context)
                        .load(imageLink)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.ivDrinkThumb, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(context)
                                        .load(imageLink)
                                        .into(holder.ivDrinkThumb, new Callback() {
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
        holder.drinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("drinkID",drinksResult.getDrinks().get(position).getIdDrink().toString());
                String id = drinks.get(position).getIdDrink();
                MainActivity.displayDrinkOffline(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
    /**
     * binding the display elements
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDrink)TextView tvDrink;
        @BindView(R.id.ivDrinkThumb) ImageView ivDrinkThumb;
        @BindView(R.id.drinkCard) CardView drinkCard;
        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}

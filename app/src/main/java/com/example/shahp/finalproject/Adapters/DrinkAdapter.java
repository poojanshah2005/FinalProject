package com.example.shahp.finalproject.Adapters;

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
import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by shahp on 02/08/2017.
 */

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.MyViewHolder> {
    DrinksResult drinksResult;
    Context context;
    public DrinkAdapter(DrinksResult drinksResult, Context context) {
        this.drinksResult = drinksResult;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_card,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String drinkName = drinksResult.getDrinks().get(position).getStrDrink();

        holder.tvDrink.setText(drinkName);
        try {
            String imageLink = (String) drinksResult.getDrinks().get(position).getStrDrinkThumb();
            if(imageLink != null) {
                Log.i("onBindViewHolder", imageLink);
                Picasso
                        .with(context)
                        .load(imageLink)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.ivDrinkThumb);
            }
        }
        catch(Exception e){
            Log.i("Error", e.getMessage());
            Log.i("Error", String.valueOf(e.getCause()));
        }
        holder.drinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("drinkID",drinksResult.getDrinks().get(position).getIdDrink().toString());
                MainActivity.displayDrink(drinksResult.getDrinks().get(position).getIdDrink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinksResult.getDrinks().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDrink;
        ImageView ivDrinkThumb;
        CardView drinkCard;
        public MyViewHolder(View v) {
            super(v);
            tvDrink = v.findViewById(R.id.tvDrink);
            ivDrinkThumb = v.findViewById(R.id.ivDrinkThumb);
            drinkCard = v.findViewById(R.id.drinkCard);
        }
    }
}

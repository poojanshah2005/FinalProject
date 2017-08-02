package com.example.shahp.finalproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shahp.finalproject.Models.drinksResult.DrinksResult;
import com.example.shahp.finalproject.R;

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
        String imageLink = (String) drinksResult.getDrinks().get(position).getStrDrinkThumb();
        holder.tvDrink.setText(drinkName);
    }

    @Override
    public int getItemCount() {
        return drinksResult.getDrinks().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDrink;
        ImageView ivDrinkThumb;
        public MyViewHolder(View v) {
            super(v);
            tvDrink = (TextView) v.findViewById(R.id.tvDrink);
            ivDrinkThumb = (ImageView) v.findViewById(R.id.ivDrinkThumb);
        }
    }
}

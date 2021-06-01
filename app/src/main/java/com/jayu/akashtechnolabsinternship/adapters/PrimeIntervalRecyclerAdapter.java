package com.jayu.akashtechnolabsinternship.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jayu.akashtechnolabsinternship.R;

public class PrimeIntervalRecyclerAdapter extends RecyclerView.Adapter<PrimeIntervalRecyclerAdapter.ViewHolder> {

    private String[] primeNumberArray;

    public PrimeIntervalRecyclerAdapter(String[] array){
        primeNumberArray = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prime_number_interval_recycler_view_single_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getRecyclerTextView().setText(primeNumberArray[position]);
    }

    @Override
    public int getItemCount() {
        return primeNumberArray.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView recyclerTextView;

        public ViewHolder(View view){
            super(view);
            recyclerTextView = (TextView) view.findViewById(R.id.txtPrimeNumberIntervalAnswer);
        }

        public TextView getRecyclerTextView(){
            return  recyclerTextView;
        }
    }
}

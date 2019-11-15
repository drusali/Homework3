package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.homework3.Activity.CatDetailActivity;
import com.example.homework3.Model.Cats;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private List<Cats> catToAdapt;

    public void setData(List<Cats> articlesToAdapt) {

        this.catToAdapt = articlesToAdapt;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat, parent, false);


        CatViewHolder articleViewHolder = new CatViewHolder(view);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Cats catAtPosition = catToAdapt.get(position);

        holder.catType.setText(catAtPosition.getName());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("id", catAtPosition.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (catToAdapt.size() == 0 ) {
            return 0;
        }
        return catToAdapt.size();
    }


    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catType;



        public CatViewHolder(View v) {
            super(v);
            view = v;
            catType = v.findViewById(R.id.catType);

        }
    }
}

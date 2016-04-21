package sointeractive.pl.starwarsapi;

/**
 * Thanks Again
 * Created by Łukasz Marczak
 *
 * @since 2016-04-21.
 * Copyright © 2015 SoInteractive S.A. All rights reserved.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;


public class StarWarsAdapter extends RecyclerView.Adapter<StarWarsAdapter.ItemViewHolder> {

    public static final String TAG = StarWarsAdapter.class.getSimpleName();

    ArrayList<StarWarsCharacter> dataSet = new ArrayList<>();

    public StarWarsAdapter(ArrayList<StarWarsCharacter> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.star_wars_character_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder ");
        StarWarsCharacter item = dataSet.get(position);
        //bind views here
        holder.name.setText(item.getName());
        holder.mass.setText(item.getMass() + " kg");
        holder.height.setText(item.getHeight() + " m");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        //root in hierarchy
        public View view;

        public TextView name;
        public TextView mass;
        public TextView height;

        public ItemViewHolder(View view) {
            super(view);
            view = itemView;
            name = (TextView) view.findViewById(R.id.name);
            mass = (TextView) view.findViewById(R.id.mass);
            height = (TextView) view.findViewById(R.id.height);
        }
    }
}

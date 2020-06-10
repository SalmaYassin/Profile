package com.example.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRecycAdapter extends RecyclerView.Adapter<CategoryRecycAdapter.favouriteHolder> {
    private ArrayList<CategoryitemModel> favList;

    public CategoryRecycAdapter(ArrayList<CategoryitemModel> favList) {
        this.favList = favList;
    }

    @NonNull
    @Override
    public favouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categ, parent, false);
        return new favouriteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull favouriteHolder holder, int position) {
        Picasso.get().load(favList.get(position).getImage()).into(holder.Item_Image);
        holder.Item_Name.setText(favList.get(position).getName());
        holder.Item_Price.setText(favList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    class favouriteHolder extends RecyclerView.ViewHolder {
        TextView Item_Name;
        TextView Item_Price ;
        ImageView Item_Image;
        //ImageView Icon_Image ;

        public favouriteHolder(@NonNull View itemView) {
            super(itemView);
            Item_Image = itemView.findViewById(R.id.itemImage);
            Item_Name = itemView.findViewById(R.id.itemName);
            Item_Price = itemView.findViewById(R.id.itemPrice);
            //Icon_Image = itemView.findViewById(R.id.iconImage);
        }
    }

}


package com.example.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapterCategory extends RecyclerView.Adapter<RVAdapterCategory.ItemViewHolder > {
    private ArrayList<ItemModel> itemList;
    private ItemClickListener onItemClickListener;

    public RVAdapterCategory(ArrayList<ItemModel> itemList, ItemClickListener onItemClickListener) {
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_similar_viewed, parent, false);
        return new ItemViewHolder(rootView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setDatainView(itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



    //Inner Class ViewHolder
    public  static class ItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImage ;
        TextView itemName ;
        TextView price ;
        View rootView;
        ItemClickListener itemClickListener;



        public ItemViewHolder(@NonNull View itemView ,final ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener =itemClickListener;
            rootView = itemView ;
            itemImage = (ImageView)itemView.findViewById(R.id.similar_image);
            itemName =(TextView)itemView.findViewById(R.id.item_name);
            price=(TextView)itemView.findViewById(R.id.price);
        }

        public void setDatainView(final ItemModel item) {

            itemImage.setImageResource(item.getImageResourceId());
            itemName.setText(item.getItemName());
            price.setText(item.getPrice());
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }

    interface ItemClickListener {
        void onItemClick(ItemModel item);
    }
}

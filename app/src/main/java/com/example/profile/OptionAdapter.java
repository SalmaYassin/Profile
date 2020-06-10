package com.example.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class OptionAdapter extends ArrayAdapter<String> {
    public OptionAdapter(Context context, ArrayList<String> list) {
        super(context, 0 , list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        String currentOption = getItem(position);

        TextView option = (TextView) listItemView.findViewById(R.id.tv_option);
        option.setText(currentOption);


        return listItemView;
    }
}

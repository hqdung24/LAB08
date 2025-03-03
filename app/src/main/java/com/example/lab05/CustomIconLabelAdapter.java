package com.example.lab05;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomIconLabelAdapter extends ArrayAdapter<String> {
    private int selectedPosition = -1;
    Context context; Integer[] thumbnails; String[] items;

    public CustomIconLabelAdapter(Context context, int layoutToBeInflated, String[] items,  Integer[] thumbnails) {
        super(context, R.layout.custom_icon, items);
        this.context = context;
        this.thumbnails = thumbnails;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_icon, null);
        TextView name = (TextView) row.findViewById(R.id.studentID);
        ImageView image = (ImageView) row.findViewById(R.id.contact_image);
        name.setText(items[position]);
        image.setImageResource(thumbnails[position]);

        return (row);
    }

} // CustomAdapter
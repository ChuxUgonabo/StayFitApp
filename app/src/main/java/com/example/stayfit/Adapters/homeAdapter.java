package com.example.stayfit.Adapters;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stayfit.R;

import java.util.ArrayList;
import java.util.List;

public class homeAdapter extends BaseAdapter {
    List<Integer> imageList = new ArrayList<>();

    public homeAdapter(List<Integer> list){
        imageList = list;
    }
    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
            convertView = myLayoutInflater.inflate(R.layout.layout_external_home, parent, false);
        }



        int item = imageList.get(position);

        if(item > 0){
            ImageView imageViewItem = convertView.findViewById(R.id.imageViewForItem);
            Drawable img = parent.getResources().getDrawable(imageList.get(position));
            imageViewItem.setImageDrawable(img);
            img.setBounds(0,0,100,100);
            imageViewItem.setPadding(5, 0, 5, 0);

        }
        return convertView;
    }
}

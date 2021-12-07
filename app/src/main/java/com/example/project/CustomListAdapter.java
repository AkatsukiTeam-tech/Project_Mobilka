package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Entities.Purchases;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<Purchases> listData;
    private LayoutInflater inflater;
    private Context context;

    public CustomListAdapter(List<Purchases> listData, LayoutInflater inflater, Context context) {
        this.listData = listData;
        this.inflater = inflater;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_purchases_layout, null);
            holder = new ViewHolder();
            holder.film_image = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.film_name = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.time_and_place = (TextView) convertView.findViewById(R.id.textView_population);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Purchases purchase = this.listData.get(position);
        System.out.println(purchase.getFilm());
        holder.film_name.setText("purchase.getFilm()");
        holder.time_and_place.setText(purchase.getDate() + " / " + purchase.getTime() + ", " + "7 ряд - 11 место");


        return convertView;
    }

    static class ViewHolder {
        ImageView film_image;
        TextView film_name;
        TextView time_and_place;
    }
}

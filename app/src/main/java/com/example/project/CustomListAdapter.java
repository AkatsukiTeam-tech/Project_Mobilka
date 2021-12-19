package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Activities.DetailsFilmActivity;
import com.example.project.Entities.Films;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<Films> listData;
    private LayoutInflater inflater;
    private Context context;

    public CustomListAdapter(List<Films> listData, LayoutInflater inflater, Context context) {
        this.listData = listData;
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_view_movie, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.movie_image);
            holder.name = (TextView) convertView.findViewById(R.id.movie_name);
            holder.genre = (TextView) convertView.findViewById(R.id.movie_genre);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Films films = this.listData.get(i);
        holder.name.setText(films.getFilm_ru_name());
        holder.genre.setText(films.getGenres().get(0).getGenre_name());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsFilmActivity.class)
                        .putExtra("films", films);
                context.startActivity(intent);
            }
        });

        URL url = null;
        Bitmap bmp = null;

        try {
            url = new URL(films.getImage_url());
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        holder.image.setImageBitmap(bmp);

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView genre;
    }
}

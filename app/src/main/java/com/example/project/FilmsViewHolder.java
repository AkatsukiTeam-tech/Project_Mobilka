package com.example.project;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilmsViewHolder extends RecyclerView.ViewHolder {

    ImageView film_image_view;
    TextView film_name_view;
    TextView film_genre_view;

    public FilmsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.film_image_view = (ImageView) itemView.findViewById(R.id.film_image);
        this.film_name_view = (TextView) itemView.findViewById(R.id.film_name);
        this.film_genre_view = (TextView) itemView.findViewById(R.id.film_genre);
    }
}

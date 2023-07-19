package com.example.walli;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

public class WallpaperViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    LottieAnimationView animationView;
    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_wallpaper_img);
        animationView = itemView.findViewById(R.id.loading_screen);
    }

}

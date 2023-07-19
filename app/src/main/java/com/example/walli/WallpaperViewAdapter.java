package com.example.walli;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WallpaperViewAdapter extends RecyclerView.Adapter<WallpaperViewHolder>{
    List<WallpaperModel> wallpaperModelsList;

    Context context;
    WallpaperList fragment;
    public WallpaperViewAdapter(List<WallpaperModel> wallpaperModels, Context context, WallpaperList wallpaperList) {
        this.wallpaperModelsList = wallpaperModels;
        this.context = context;
        this.fragment = wallpaperList;
    }


    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_wallpaper,parent,false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        WallpaperModel wallpaperModel = wallpaperModelsList.get(position);
        //holder.animationView.playAnimation();
        Glide.with(fragment.requireContext()).load(wallpaperModel.getImageUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.animationView.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imageView);
        // Log.d("lnk",wallpaperModel.getImageUrl());
//        Picasso.get().load(wallpaperModel.getImageUrl()).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                holder.imageView.setImageBitmap(bitmap);
//                holder.animationView.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });
        //Picasso.get().load(wallpaperModel.getImageUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                Toast.makeText(context, ""+wallpaperModelsList.get(pos).getImageId(), Toast.LENGTH_SHORT).show();
                Fragment wallpaperView = new WallpaperView(wallpaperModel);
                fragment.getParentFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  //popExit
                ).replace(R.id.nav_host_fragment_content_home_page, wallpaperView, wallpaperView.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });

    }

//    @Override
//    public void onBindViewHolder(@NonNull WallpaperViewAdapter.WallpaperViewHolder holder, int position) {
//
//    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return wallpaperModelsList.size();
    }


}

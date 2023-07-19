package com.example.walli;


/// categories ke wallpaper show karga click ke baad fragment

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WallpaperList extends Fragment {


    RecyclerView recyclerView;
    WallpaperModel wallpaperModel;

    private TextView category;
    String imageCategory;
    ImageView backButton;
    public WallpaperList(String imageCat) {
        // Required empty public constructor
        imageCategory = imageCat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallpaper_list, container, false);
        recyclerView = view.findViewById(R.id.wallpapers_list);
        category = view.findViewById(R.id.list_title);
        backButton = view.findViewById(R.id.back_arrow_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        category.setText(imageCategory);
        backButton.setOnClickListener((v -> {
            getParentFragmentManager().popBackStackImmediate();
        }));
        getWallpapers();
    }

    private void getWallpapers() {
        List<WallpaperModel> wallpaperModelList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        WallpaperViewAdapter wallpaperViewAdapter = new WallpaperViewAdapter(wallpaperModelList,getContext(),this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(wallpaperViewAdapter);

        DatabaseReference myRef = database.getReference("CategoryBackground").child(imageCategory);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.d("data",snapshot.toString());
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String imageUrl = ds.child("imageLink").getValue(String.class);
                    String imageName = ds.child("name").getValue(String.class);
                    Log.d("data",id +","+imageUrl+","+imageCategory);
                    wallpaperModel = new WallpaperModel();
                    wallpaperModel.setImageId(id);
                    wallpaperModel.setImageUrl(imageUrl);
                    wallpaperModel.setImageName(imageName);
                    wallpaperModelList.add(wallpaperModel);
                    //Log.d("wallaper",wallpaperModel.getImageId());
                }
                wallpaperViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DB error",error.toString());
            }
        });
        //Log.d("d", wallpaperModelList.get(0).getImageId());



    }
}
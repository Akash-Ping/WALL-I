package com.example.walli;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * * Single wallpaper ke liye fragment
 * create an instance of this fragment.
 */
public class WallpaperView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    URL url;
    AsyncTask mMyTask;

    private ImageView imageView;
    private TextView setWallpaper;
    private ImageView downloadButton, shareButton, backButton;

    WallpaperModel wallpaperModel;

    public WallpaperView(WallpaperModel wallpaperModel) {
        // Required empty public constructor
        this.wallpaperModel = wallpaperModel;
    }


    // TODO: Rename and change types and number of parameters
//    public static WallpaperView newInstance(String param1, String param2) {
//        WallpaperView fragment = new WallpaperView();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Picasso.get().load(wallpaperModel.getImageUrl()).into(imageView);
        //constraintLayout.setBackground(imageView.getDrawable());
        backButton.setOnClickListener((v -> {
            getParentFragmentManager().popBackStackImmediate();

        }));
        setWallpaper.setOnClickListener((v) ->{
            Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            imageView.buildDrawingCache();
            Bitmap bitmap = imageView.getDrawingCache();
            WallpaperManager m=WallpaperManager.getInstance(getContext());

            try {
                //m.setBitmap(bitmap,null,true,WallpaperManager.FLAG_LOCK);
                m.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        downloadButton.setOnClickListener((v) ->{
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {
                // You can use the API that requires the permission.
                Toast.makeText(requireActivity(), "Dowloading...", Toast.LENGTH_SHORT).show();
                DownloadImageTask task = new DownloadImageTask();
                task.execute(wallpaperModel.getImageUrl());

            } else {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1 );
//                Toast.makeText(getActivity(), "Need download permission for this", Toast.LENGTH_SHORT).show();
            }
        });
        shareButton.setOnClickListener((v) ->{
            Picasso.get().load(wallpaperModel.getImageUrl()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    SaveImageTask task = new SaveImageTask();
                    task.execute(wallpaperModel.getImageUrl());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    // intent banaya put extra ka use kiya uske andar parameter pass kiya bit map pe
                    intent.putExtra(Intent.EXTRA_STREAM, getBitmap(bitmap));
                    startActivity(Intent.createChooser(intent, "Share Image"));
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallpaper_view, container, false);
        imageView = view.findViewById(R.id.wallpaper_view_img);
        setWallpaper = view.findViewById(R.id.set_wallpaper_text);
        downloadButton = view.findViewById(R.id.download_wallpaper_btn);
        shareButton = view.findViewById(R.id.share_wallpaper_btn);
        backButton = view.findViewById(R.id.back_button);
        return view;
    }

    private Uri getBitmap(Bitmap bitmap){
        Uri uri = null;
        try{
            File file = new File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES),wallpaperModel.getImageName() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.close();
            uri = FileProvider.getUriForFile(requireContext(), requireActivity().getPackageName()+".fileprovider", file);

        }catch (IOException e){
            e.printStackTrace();
        }
        return uri;
    }
    private class SaveImageTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                // Save the bitmap to a file
                String fileLocation = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    fileLocation = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + wallpaperModel.getImageName() + ".png";
                }
                FileOutputStream out = new FileOutputStream(fileLocation);
                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                Log.d("done","file downloaded");
                // Clean up
                out.flush();
                out.close();
                input.close();
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
//                    Toast toast = Toast.makeText(requireActivity(), "File downloaded", Toast.LENGTH_SHORT);
//                    toast.show();
                }
            });
            return null;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                // Save the bitmap to a file
                String fileLocation = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    fileLocation = "storage/emulated/0/Download" + "/" + wallpaperModel.getImageName() + ".jpg";
                }
                FileOutputStream out = new FileOutputStream(fileLocation);
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                Log.d("done","file downloaded");
                // Clean up
                out.flush();
                out.close();
                input.close();
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(requireActivity(), "File downloaded", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return null;
        }
    }




}
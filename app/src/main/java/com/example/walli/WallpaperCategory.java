package com.example.walli;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WallpaperCategory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DrawerLayout drawerLayout;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,imageView;
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,text17,text18,text19;

    public WallpaperCategory(){

    }
    public WallpaperCategory(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
//    public static WallpaperCategory newInstance(String param1, String param2) {
//        WallpaperCategory fragment = new WallpaperCategory();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallpaper_category, container, false);
        imageView = view.findViewById(R.id.menu);
        imageView.setOnClickListener((v) ->{
            drawerLayout.openDrawer(GravityCompat.START);
        });
        img1 = view.findViewById(R.id.category_animals_img);
        text1 = view.findViewById(R.id.category_animals_title);

        img2 = view.findViewById(R.id.category_architecture_img);
        text2 = view.findViewById(R.id.category_architecture_title);

        img3 = view.findViewById(R.id.category_arts_culture_img);
        text3 = view.findViewById(R.id.category_arts_culture_title);

        img4 = view.findViewById(R.id.category_business_work_img);
        text4 = view.findViewById(R.id.category_business_work_title);

        img5 = view.findViewById(R.id.category_cars_img);
        text5 = view.findViewById(R.id.category_cars_title);

        img6 = view.findViewById(R.id.category_cities_img);
        text6 = view.findViewById(R.id.category_cities_title);

        img7 = view.findViewById(R.id.category_experimental_img);
        text7 = view.findViewById(R.id.category_experimental_title);

        img8 = view.findViewById(R.id.category_marvel_img);
        text8 = view.findViewById(R.id.category_marvel_title);

        img9 = view.findViewById(R.id.category_minimalist_img);
        text9 = view.findViewById(R.id.category_minimalist_title);

        img10 = view.findViewById(R.id.category_nature_img);
        text10 = view.findViewById(R.id.category_nature_title);

        img11 = view.findViewById(R.id.category_people_img);
        text11 = view.findViewById(R.id.category_people_title);

        img12 = view.findViewById(R.id.category_space_img);
        text12 = view.findViewById(R.id.category_space_title);

        img13 = view.findViewById(R.id.category_spirituality_img);
        text13 = view.findViewById(R.id.category_spirituality_title);

        img14 = view.findViewById(R.id.category_sports_img);
        text14 = view.findViewById(R.id.category_sports_title);

        img15 = view.findViewById(R.id.category_technology_img);
        text15 = view.findViewById(R.id.category_technology_title);

        img16 = view.findViewById(R.id.category_texture_patterns_img);
        text16 = view.findViewById(R.id.category_texture_title);

        img17 = view.findViewById(R.id.category_travel_img);
        text17 = view.findViewById(R.id.category_travel_title);

        img18 = view.findViewById(R.id.category_miscellaneous_img);
        text18 = view.findViewById(R.id.category_miscellaneous_title);

        img19 = view.findViewById(R.id.category_anime_img);
        text19 = view.findViewById(R.id.category_anime_title);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Animal%2FAnimal-tiger.jpg?alt=media&token=3dce201e-4405-4faf-ae06-84bea2780d5f")
                        .into(img1);
        img1.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text1.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Architecture%2Farchitecture-1.jpg?alt=media&token=11b6b7a9-6bfd-4820-b982-cba468fcd82e")
                .into(img2);
        img2.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text2.getText().toString()));

        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Art%20%26%20Culture%2Fart-8.jpg?alt=media&token=09479f6d-362f-42f2-bd6e-268e1c26afd8")
                .into(img3);
        img3.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text3.getText().toString()));

        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Business%20%26%20Work%2FBusiness-4.jpg?alt=media&token=c54ec87e-425d-4e88-88a8-a2b97ab1c513")
                .into(img4);
        img4.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text4.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Cars%2Fcars-6.jpg?alt=media&token=2ac594bb-6bb0-4dc4-9a99-203b0744a963")
                .into(img5);
        img5.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text5.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Cities%2Fcities-1.jpg?alt=media&token=d62a5eeb-6f0a-43d1-9322-182ce5da74fe")
                .into(img6);
        img6.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text6.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Experimental%2Fexperimental-4.jpg?alt=media&token=01a2e884-d904-4e35-b296-f96bdef19203")
                .into(img7);
        img7.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text7.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Marvel%2Fmarvel-4.jpg?alt=media&token=a15d9107-c6d4-4413-adf3-a6bc18da4cfe")
                .into(img8);
        img8.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text8.getText().toString()));



        });


        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Minimalist%2Fminimalist-4.jpg?alt=media&token=04a2e6ec-e0dc-46d4-8c4d-3b87d2e35a04")
                .into(img9);
        img9.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text9.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Nature%2Fnature-7.jpg?alt=media&token=fd09549c-e4d8-403e-bde5-d3d1afa91584")
                .into(img10);
        img10.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text10.getText().toString()));

        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/People%2Fpeople-6.jpg?alt=media&token=8659534d-718e-4c2f-9873-316428c4b2c3")
                .into(img11);
        img11.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text11.getText().toString()));

        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Space%2Fspace-7.jpg?alt=media&token=92973e6f-db33-46b1-a0ce-e06998ec45c0")
                .into(img12);
        img12.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text12.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Spirituality%2FSpirituality-3.jpg?alt=media&token=f0aa7d5c-ef08-4b87-bc2b-c4199108ba62")
                .into(img13);
        img13.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text13.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Sports%2Fsports-7.jpg?alt=media&token=5be87d22-9408-42f3-af44-0b92395480d1")
                .into(img14);
        img14.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text14.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Technology%2Ftechnology-8.jpg?alt=media&token=f7d64051-1dda-42b7-b5b2-8d28ffb12b58")
                .into(img15);
        img15.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text15.getText().toString()));


        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Texture%20%26%20Patterns%2Ftexture-6.jpg?alt=media&token=4c5f9904-c4dc-415f-9655-65c23e5e4fd6")
                .into(img16);
        img16.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text16.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Travel%2Ftravel-3.jpg?alt=media&token=2c75a469-03af-4b8c-94df-af7ccb09f213")
                .into(img17);
        img17.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text17.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Miscellaneous%2Fmiscellaneous-4.jpeg?alt=media&token=900fd7d5-9c6d-4ab2-9569-477823a996e0")
                .into(img18);
        img18.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text18.getText().toString()));



        });

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/walli-f2493.appspot.com/o/Anime%2FAnime-5.jpg?alt=media&token=e3df49aa-8bd0-43ec-8f29-86c8c4c0fcf2")
                .into(img19);
        img19.setOnClickListener((v) -> {
            openFragment(new WallpaperList(text19.getText().toString()));



        });




        return view;
    }
    private void openFragment(Fragment fragment){
        getParentFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  //popExit
                )
                .replace(R.id.nav_host_fragment_content_home_page, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
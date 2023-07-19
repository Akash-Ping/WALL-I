package com.example.walli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;


// main activity

public class mainscreen extends AppCompatActivity{

    AppBarConfiguration appBarConfiguration;
    NavigationView navigationView;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(mainscreen.this,MainActivity.class);
    }

//    @Override
//    protected void onNightModeChanged(int mode) {
//        super.onNightModeChanged(mode);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        SharedPreferences sf = getSharedPreferences("mySharedDB",MODE_PRIVATE);
        sf.edit().putInt("status",1).apply();

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);



        navigationView = findViewById(R.id.navigation_view);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(findViewById(R.id.toolbar));
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_about,R.id.nav_rate)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        getSupportActionBar().hide();



        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new WallpaperCategory(drawerLayout);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  //popExit
        ).replace(R.id.nav_host_fragment_content_home_page, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                navigationView.getCheckedItem().setChecked(false);
                navigationView.setCheckedItem(id);

                if(id == R.id.nav_home){
                    Fragment fragment = new WallpaperCategory(drawerLayout);
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  //popExit
                    ).replace(R.id.nav_host_fragment_content_home_page, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.nav_about) {
                    Fragment fragment = new BlankFragment(drawerLayout);
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  //popExit
                    ).replace(R.id.nav_host_fragment_content_home_page, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

        View headerview = navigationView.getHeaderView(0);
        ImageView imageButton = (ImageView)
                headerview.findViewById(R.id.imageViewGit);
        imageButton.setOnClickListener((v -> {
            String url = "https://github.com/Akash-Ping";
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }));
//
    }


}
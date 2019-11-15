package com.example.homework3.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.homework3.Fragments.CatRecyclerFragment;
import com.example.homework3.Fragments.favfragment;
import com.example.homework3.Model.FavCats;
import com.example.homework3.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    public static ArrayList<FavCats> favCats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favCats = new ArrayList<FavCats>();
        Fragment fragment = new CatRecyclerFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.navigation_seach) {
                    Fragment fragment = new CatRecyclerFragment();
                    swapFragment(fragment);
                    return true;

                } else if (menuItem.getItemId() == R.id.navigation_favourites) {
                    Fragment fragment = new favfragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

    }


    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }

}



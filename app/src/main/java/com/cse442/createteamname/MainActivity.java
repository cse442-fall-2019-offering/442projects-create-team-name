package com.cse442.createteamname;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.cse442.createteamname.ui.results.ResultsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements Restaurant_Information.OnFragmentInteractionListener {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_settings, R.id.navigation_home, R.id.navigation_add)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    //don't know button links us to randomized list of restaurants
    public void buttonClickShowResults(View v) {
          Navigation.findNavController(v).navigate(R.id.action_home_to_results);
    }

    //entered text in searchView gets read
    public void search_input(View v){
        final SearchView search_view = (SearchView)v.findViewById(R.id.i_know_q);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                get_Search(search_view.getQuery().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    //gets the search query so it can be passed on to other functions for the DB
    public String get_Search(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        return s;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
                navController.popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

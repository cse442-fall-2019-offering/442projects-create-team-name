package com.cse442.createteamname;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

import com.cse442.createteamname.ui.results.ResultsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Restaurant_Information.OnFragmentInteractionListener {

    private NavController navController;
    private String tag1;
    private String tag2;
    private String tag3;

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

    public void buttonClickShowResults(View v) {
          Navigation.findNavController(v).navigate(R.id.action_home_to_results);
    }

    //entered text in searchView gets read
    public void search_input(View v){
        final SearchView search_view = (SearchView)v.findViewById(R.id.i_know_q);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        set_Search(search_view.getQuery().toString());
    }

    //gets the search query so it can be passed on to other functions for the DB
    public void set_Search(String s){
        //The toast below is the test
        //Toast.makeText(getApplicationContext(), s.toLowerCase(), Toast.LENGTH_SHORT).show();
        s.toLowerCase();

        //sSeparates string input by comma
        List<String> input_List = Arrays.asList(s.split(","));

        //set the different input tags
        if (input_List.size() >= 1) {
            String str1 = input_List.get(0);
            tag1 = str1.replace(" ", "");
        }
        if (input_List.size() >= 2) {
            String str2 = input_List.get(1);
            tag2 = str2.replace(" ", "");
        }
        if (input_List.size() >= 3) {
            String str3 = input_List.get(2);
            tag3 = str3.replace(" ", "");
        }
    }

    public String get_tag1(){
        return tag1;
    }

    public String get_tag2(){
        return tag2;
    }

    public String get_tag3(){
        return tag3;
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

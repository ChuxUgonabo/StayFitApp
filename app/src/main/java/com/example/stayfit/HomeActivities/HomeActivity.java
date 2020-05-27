package com.example.stayfit.HomeActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.stayfit.Adapters.homeAdapter;
import com.example.stayfit.MainActivities.FirstActivity;
import com.example.stayfit.MainActivities.MainActivity;
import com.example.stayfit.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    List<Integer> sectionList = new ArrayList<>();

    private DrawerLayout myDrawLayout;
    private ActionBarDrawerToggle myABDT;

    public void addSections(){
        sectionList.add(R.drawable.workout);
        sectionList.add(R.drawable.stopwatch);
        sectionList.add(R.drawable.report);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addSections();
        myDrawLayout = (DrawerLayout) findViewById(R.id.myDL);
        myABDT = new ActionBarDrawerToggle(this, myDrawLayout, R.string.Open, R.string.Close);
        myABDT.setDrawerIndicatorEnabled(true);

        myDrawLayout.addDrawerListener(myABDT);
        myABDT.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView myNavView = (NavigationView) findViewById(R.id.nav_view);

        myNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.itemMyProfile:
                        Toast.makeText(HomeActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.itemReport:
                        startActivity(new Intent(HomeActivity.this, ReportActivity.class));
                        break;

                    case R.id.itemSignout:
                        startActivity(new Intent(HomeActivity.this, FirstActivity.class));
                        break;
                }

                return true;
            }
        });
        ListView listViewHome = (ListView) findViewById(R.id.homeView);

        homeAdapter myHomeAdapter = new homeAdapter(sectionList);

        listViewHome.setAdapter(myHomeAdapter);

        listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        startActivity(new Intent(HomeActivity.this, WorkoutActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(HomeActivity.this, Stopwatch.class));
                        break;

                    case 2:
                        startActivity(new Intent(HomeActivity.this, ReportActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return myABDT.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}

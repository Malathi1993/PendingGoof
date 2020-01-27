package com.encrypts.goofyturtle;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.encrypts.goofyturtle.ui.Apiclient;
import com.encrypts.goofyturtle.ui.Apiinterface;
import com.encrypts.goofyturtle.ui.model.Categories;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

String response="[{\"CatagoryName\":\"Drones\",\"SiteUrl\":\"category-drones\",\"child\":[{\"categoryName\":\"Drones 1\",\"siteUrl\":\"subcategory-drones-1\"},{\"categoryName\":\"Drones 2\",\"siteUrl\":\"subcategory-drones-2\"},{\"categoryName\":\"Fixed Wing Drone\",\"siteUrl\":\"Fixed-Wing-Drone\"}]},{\"CatagoryName\":\"Pre-School\",\"SiteUrl\":\"category-pre-school\",\"child\":[{\"categoryName\":\"Pre-School 1\",\"siteUrl\":\"subcategory-pre-school-1\"},{\"categoryName\":\"Pre-School 2\",\"siteUrl\":\"subcategory-pre-school-2\"}]},{\"CatagoryName\":\"Remote Controlled\",\"SiteUrl\":\"category-remote-controlle\",\"child\":[{\"categoryName\":\"Remote Controlled 1\",\"siteUrl\":\"subcategory-remote-controlled-1\"},{\"categoryName\":\"Remote Controlled 2\",\"siteUrl\":\"subcategory-remote-controlled-2\"}]},{\"CatagoryName\":\"Interactive\",\"SiteUrl\":\"category-interactive\"},{\"CatagoryName\":\"Building Blocks\",\"SiteUrl\":\"category-building-blocks\",\"child\":[{\"categoryName\":\"Building Blocks 1\",\"siteUrl\":\"subcategory-building-blocks-1\"},{\"categoryName\":\"Building Blocks 2\",\"siteUrl\":\"subcategory-building-block-2\"}]},{\"CatagoryName\":\"STEM & SMART\",\"SiteUrl\":\"category-stem-smart\",\"child\":[{\"categoryName\":\"STEM & SMART 1\",\"siteUrl\":\"subcategory-stem-smart-1\"},{\"categoryName\":\"STEM & SMART 2\",\"siteUrl\":\"subcategory-stem-smart-2\"}]},{\"CatagoryName\":\"Collectibles\",\"SiteUrl\":\"category-collectibles\"},{\"CatagoryName\":\"Puzzles & Games\",\"SiteUrl\":\"category-puzzles-games\",\"child\":[{\"categoryName\":\"Puzzles & Games 1\",\"siteUrl\":\"subcategory-puzzles-game-1\"},{\"categoryName\":\"Rubics Cubes\",\"siteUrl\":\"Testing-Team\"}]},{\"CatagoryName\":\"Dolls\",\"SiteUrl\":\"Dolls\",\"child\":[{\"categoryName\":\"Barbie\",\"siteUrl\":\"Barbie\"}]}]";
    private AppBarConfiguration mAppBarConfiguration;
    Apiinterface apiInterface;
    NavigationView navigationView;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<Categories> headerList = new ArrayList<>();
    HashMap<Categories, List<Categories>> childList = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = findViewById(R.id.expandableListView);
        //prepareMenuData();
        //populateExpandableList();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View view){
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        });
        apiInterface =Apiclient.getClient().

                create(Apiinterface .class);
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView =findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration =new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_gallery,R.id.nav_slideshow,
                R.id.nav_tools,R.id.nav_share,R.id.nav_send).setDrawerLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);


        /*LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
        layout.setBackgroundColor(Color.RED);
        View inflate = getLayoutInflater().inflate(R.layout.option_menu, null);
        navigationView.addView(inflate);*/


        Call<List<Categories>> call = apiInterface.getCategories();
        final NavigationView finalNavigationView = navigationView;
        call.enqueue(new Callback<List<Categories>>()

        {
            @Override
            public void onResponse
            (Call < List < Categories >> call, Response < List < Categories >> response){

            Log.d("TAG", response.code() + "");
            List<Categories> responseList = response.body();
            Menu menu = finalNavigationView.getMenu();

            for (int i = 0; i < responseList.size(); i++) {
                menu.add(responseList.get(i).getCatagoryName());
                if (responseList.get(i).getChild() != null && responseList.get(i).getChild().size() > 0) {
                    for (int j = 0; j < responseList.get(i).getChild().size(); j++) {
                        menu.add(responseList.get(i).getChild().get(j).getCategoryName());
                    }
                }
            }
        }
            @Override
            public void onFailure (Call < List < Categories >> call, Throwable t){

        }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//         switch (item.getItemId()) {
//             case:
//
//         default:
//
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}

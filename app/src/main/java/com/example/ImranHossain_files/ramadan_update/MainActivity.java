package com.example.ImranHossain_files.ramadan_update;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DatePicker datePicker;
    Date_database date_database;
    private Button button;
    private TextView text;
    private TextView datetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        button = findViewById(R.id.button);
        text = findViewById(R.id.t);
        datePicker = findViewById(R.id.date_picker);
        date_database = new Date_database(this);
        // SQLiteDatabase sqLiteDatabase = date_database.getWritableDatabase();

        text.setText(currentdate());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text.setText(currentdate());
                String uname = text.getText().toString();
                Boolean result = date_database.search(uname);

                if (result == true) {
                    String unamee = text.getText().toString().trim();
                    String uname2 = date_database.searchinfo(unamee);


                    Intent intent = new Intent(MainActivity.this, ShowSearchResult.class);
                    intent.putExtra("data", uname2);
                    startActivity(intent);
                } else {
                   text.setText("This date is not found");
                }


            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    String currentdate() {

        StringBuilder stringBuilder = new StringBuilder();

        // stringBuilder.append("Current date: ");
        stringBuilder.append(datePicker.getDayOfMonth() + "/");
        stringBuilder.append((datePicker.getMonth() + 1) + "/");
        stringBuilder.append(datePicker.getYear());

        return stringBuilder.toString();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_date) {

            Intent intent = new Intent(MainActivity.this, Add.class);
            startActivity(intent);


        } else if (id == R.id.nav_list) {


            Intent intent = new Intent(MainActivity.this, List_data.class
            );
            startActivity(intent);

        } else if (id == R.id.nav_tojbi) {

            Intent intent = new Intent(MainActivity.this, Tojbi.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {


            Intent intent = new Intent(MainActivity.this, Email.class
            );
            startActivity(intent);

        } else if (id == R.id.about) {


            Intent intent = new Intent(MainActivity.this, About.class
            );
            startActivity(intent);

        } else if (id == R.id.exit) {


            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

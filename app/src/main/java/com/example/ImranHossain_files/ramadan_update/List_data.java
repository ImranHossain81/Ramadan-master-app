package com.example.ImranHossain_files.ramadan_update;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List_data extends AppCompatActivity {


    private ListView listView1;
    private Date_database date_database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView1 = findViewById(R.id.list1);


        date_database = new Date_database(this);
        loadData1();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ho) {
            Intent intent = new Intent(List_data.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void loadData1() {

        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = date_database.showAllData();

        if (cursor.getCount() == 0) {


            Toast.makeText(getApplicationContext(), "No data is available in database !!", Toast.LENGTH_LONG).show();
        } else {

            while (cursor.moveToNext()) {
                listData.add("\t\tতারিখ : " + cursor.getString(1) + "\n\t\tসেহেরি :   " + cursor.getString(2) + "\n\t\tইফতার : " + cursor.getString(3) + "\n");

            }


        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.text_view_id, listData);

        listView1.setAdapter(adapter);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(List_data.this);
                View mview = getLayoutInflater().inflate(R.layout.activity_alart_box, null);

                Button dalete = mview.findViewById(R.id.delete);
                Button edit = mview.findViewById(R.id.edit);


                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(List_data.this, Update.class);
                        startActivity(intent);

                    }
                });


                dalete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(List_data.this, Delete.class);
                        startActivity(intent);
                    }
                });

                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();


            }
        });


    }
}
package com.example.ImranHossain_files.ramadan_update;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tojbi extends AppCompatActivity {

    int count = 0;
    private Button button;
    private TextView textView,reset,  manual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tojbi);
        button = findViewById(R.id.button_to);
        textView = findViewById(R.id.text_to);
        manual = findViewById(R.id.manual);
        reset = findViewById(R.id.reset);
        textView.setText("0");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                textView.setText("[ " + count + " ]");


            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              count=0;
              textView.setText("0");
            }
        });


        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tojbi.this, Tojbi1.class);
                startActivity(intent);


            }
        });

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
            Intent intent = new Intent(Tojbi.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

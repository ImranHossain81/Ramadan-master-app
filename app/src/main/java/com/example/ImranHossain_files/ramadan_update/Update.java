package com.example.ImranHossain_files.ramadan_update;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Update extends AppCompatActivity {


    TextView textView1, textView2;
    Calendar mcalender;
    int day, month, year;
    //DatePicker datePicker;
    Date_detiles date_detiles;
    Date_database date_database;
    String date1, iftari1, sehiri1;
    private EditText ifter, seheri;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        textView1 = findViewById(R.id.date_text2);
        ifter = findViewById(R.id.ifter2);
        seheri = findViewById(R.id.seheri2);
        save = findViewById(R.id.update);
        date_database = new Date_database(this);
        textView2 = findViewById(R.id.date_war1);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textView = textView1.getText().toString();
                String ift = ifter.getText().toString();
                String seh = seheri.getText().toString();

                if (textView.isEmpty())

                {
                    textView2.setText("sorry !!You must have to select date.");

                } else {


                    Boolean isupdate = date_database.updatedata(textView, ift, seh);


                    if (isupdate == true) {

                        Toast.makeText(getApplicationContext(), " Update Successfully.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Update.this, List_data.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), " Date not found ", Toast.LENGTH_LONG).show();
                    }
                }
            }


        });


        mcalender = Calendar.getInstance();
        day = mcalender.get(Calendar.DAY_OF_MONTH);
        month = mcalender.get(Calendar.MONTH);
        year = mcalender.get(Calendar.YEAR);
        // month=month+1;
        // textView.setText(day+"/"+month+"/"+year);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Update.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {
                        monthOfyear = monthOfyear + 1;
                        textView1.setText(dayOfMonth + "/" + monthOfyear + "/" + year);

                        textView1.toString();

                    }
                }, year, month, day);
                datePickerDialog.show();
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
            Intent intent = new Intent(Update.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

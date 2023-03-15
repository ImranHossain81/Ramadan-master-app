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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Delete extends AppCompatActivity {


    Calendar mcalender;
    int day, month, year;
    //DatePicker datePicker;
    Date_detiles date_detiles;
    Date_database date_database;
    private TextView textView3, textView4, textView5;
    private Button delete1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        textView3 = findViewById(R.id.date_text3);
        delete1 = findViewById(R.id.delete1);
        date_database = new Date_database(this);
        textView4 = findViewById(R.id.date_war3);
        textView5 = findViewById(R.id.date_warning1);


        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = textView3.getText().toString();

                if (date.isEmpty()) {
                    textView4.setText("sorry !! You  must  have  to  select  date.");
                } else {
                    int value = date_database.deletedata(date);
                    if (value < 0) {
                        Intent intent = new Intent(Delete.this, List_data.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Delete Failed !", Toast.LENGTH_LONG).show();
                    } else if (value == 0) {

                        textView5.setText("Delete failed !! Date can't found.");
                    } else {
                        Intent intent = new Intent(Delete.this, List_data.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), " Delete successfully", Toast.LENGTH_LONG).show();
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

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Delete.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {
                        monthOfyear = monthOfyear + 1;
                        textView3.setText(dayOfMonth + "/" + monthOfyear + "/" + year);

                        textView3.toString();

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
            Intent intent = new Intent(Delete.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

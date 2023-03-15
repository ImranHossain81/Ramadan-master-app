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

public class Add extends AppCompatActivity {


    TextView textView, textView1,save;
    Calendar mcalender;
    int day, month, year;
    //DatePicker datePicker;
    Date_detiles date_detiles;
    Date_database date_database;
    private EditText ifter, seheri;
    private Button  update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        textView = findViewById(R.id.date_text);
        ifter = findViewById(R.id.ifter);
        seheri = findViewById(R.id.seheri);
        //save = findViewById(R.id.save);
        save = findViewById(R.id.btn_Save);




        date_database = new Date_database(this);
        date_detiles = new Date_detiles();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dateget = textView.getText().toString();
                String ifterget = ifter.getText().toString();
                String seheriget = seheri.getText().toString();

                if (textView.getText().toString().isEmpty()) {


                    textView.setError("select date");
                    textView.requestFocus();
                    return;

                } else {

                    date_detiles.setDate(dateget);
                    date_detiles.setIftari(ifterget);
                    date_detiles.setSeheri(seheriget);


                    long rowId = date_database.insertData(date_detiles);


                    if (rowId > 0) {
                        Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();


                        Intent sign = new Intent(Add.this, MainActivity.class);
                        startActivity(sign);


                    } else {
                        Toast.makeText(getApplicationContext(), "Row is failed", Toast.LENGTH_SHORT).show();
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
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {
                        monthOfyear = monthOfyear + 1;
                        textView.setText(dayOfMonth + "/" + monthOfyear + "/" + year);

                        textView.toString();

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
            Intent intent = new Intent(Add.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

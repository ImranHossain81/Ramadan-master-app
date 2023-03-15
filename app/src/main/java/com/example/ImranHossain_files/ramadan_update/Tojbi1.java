package com.example.ImranHossain_files.ramadan_update;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tojbi1 extends AppCompatActivity {
    int count = 0, c = 0;
    Vibrator vibrator;
    private Button button;
    private EditText editText;
    private TextView textView, textView1, button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tojbi1);

        button = findViewById(R.id.button_to);
        editText = findViewById(R.id.editText_to);
        textView = findViewById(R.id.count);

        button1 = findViewById(R.id.refresh);
        textView.setText("0");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = editText.getText().toString();


                if (number.isEmpty()) {


                    editText.setError("Set the value");
                    editText.requestFocus();
                    return;


                } else {


                    count++;
                    c++;

                    textView.setText("[ " + count + " ]");

                    int value = Integer.parseInt(number);

                    if (c >= value) {


                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Tojbi1.this);

                        View mview = getLayoutInflater().inflate(R.layout.alart1, null);

                        mBuilder.setView(mview);

                        AlertDialog dialog = mBuilder.create();

                        dialog.show();


                    }


                }
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editText.setText("");
                textView.setText("0");


            }
        });
    }


    void fieldCheck() {


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
            Intent intent = new Intent(Tojbi1.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
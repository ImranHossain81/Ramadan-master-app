package com.example.ImranHossain_files.ramadan_update;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Email extends AppCompatActivity {


    private TextView my_email;
    private EditText subject, messege;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);


        subject = findViewById(R.id.subject);
        messege = findViewById(R.id.massage);
        send = findViewById(R.id.save);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (messege.getText().toString().isEmpty()) {

                    messege.setError("Field can't be empty");
                    messege.requestFocus();
                    return;



                } else {
                    send_mail();
                }


            }
        });
    }

    private void send_mail() {


        String myemail = "imran15-11681@diu.edu.bd";
        String recipientlist = myemail.toString();
        String[] recipients = recipientlist.split(",");
        String suject = subject.getText().toString();
        String message = messege.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, suject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Your message is sending by your Gmail account.Click or Install the Gmail"));

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
            Intent intent = new Intent(Email.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


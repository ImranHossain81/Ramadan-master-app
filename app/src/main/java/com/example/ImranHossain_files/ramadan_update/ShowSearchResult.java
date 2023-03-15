package com.example.ImranHossain_files.ramadan_update;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowSearchResult extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search_result);

        textView = findViewById(R.id.searchresult2);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String alldata = bundle.getString("data");
            textView.setText(alldata);
        }
    }
}

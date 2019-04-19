package com.example.ezlinkemulation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ToCall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MyHostApduService.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.textViewMain);
        textView.setText(message);
    }
}

package com.messageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.blockingcallsapplication.R;

public class RetrieveMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_message);
        Intent sms_intent = getIntent();
        Bundle b = sms_intent.getExtras();
        TextView tv = (TextView) findViewById(R.id.txtview);
        if (b != null) {
            // Display SMS in the TextView
            tv.setText(b.getString("sms_str"));
        }
    }
}

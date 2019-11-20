package com.messageapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blockingcallsapplication.R;

import java.util.List;

public class SendSms extends AppCompatActivity {
    EditText txtSendNumber1,txtSendMessage1;
    Button btnSendMessage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        txtSendNumber1 = (EditText) findViewById(R.id.txtSendNumber1);
        txtSendMessage1 = (EditText) findViewById(R.id.txtSendMesssage1);
        btnSendMessage1 = (Button) findViewById(R.id.btnSMS1);

        // Attached Click Listener
        btnSendMessage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = txtSendNumber1.getText().toString();
                String message = txtSendMessage1.getText().toString();
                try {
                    if(!number.isEmpty()){
                        SmsManager smsManager = SmsManager.getDefault();
                        List<String> smsContent = smsManager.divideMessage(message);
                        for (String msg : smsContent) {

                            PendingIntent sentIntent = PendingIntent.getBroadcast(SendSms.this, 0, new Intent("SMS_SENT"), 0);
                            PendingIntent deliveredIntent = PendingIntent.getBroadcast(SendSms.this, 0, new Intent("SMS_DELIVERED"), 0);
                            smsManager.sendTextMessage(number, null, msg, sentIntent, deliveredIntent);
                            Intent ireturn = new Intent(SendSms.this, MessageBox.class);
                            startActivity(ireturn);
                            finish();
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null,message, null, null);
                Toast.makeText(SendSms.this, "Message sent successfully", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}

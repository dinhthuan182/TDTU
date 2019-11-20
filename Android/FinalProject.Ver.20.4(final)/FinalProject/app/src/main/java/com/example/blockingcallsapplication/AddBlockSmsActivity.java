package com.example.blockingcallsapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;

public class AddBlockSmsActivity extends AppCompatActivity {
    private EditText edSmsNumber, edSmsName;
    private Button btnSmsReset;
    private Button btnSmsSubmit;
    private ImageButton btnSmsContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_block_sms);

        edSmsNumber = (EditText) findViewById(R.id.edSmsNumber);
        edSmsName = (EditText) findViewById(R.id.edSmsName);
        btnSmsReset = (Button) findViewById(R.id.btnSmsReset);
        btnSmsSubmit = (Button) findViewById(R.id.btnSmsSubmit);
        btnSmsContact =(ImageButton) findViewById(R.id.btnSmsContact);

        btnSmsContact.setImageResource(R.drawable.contact);
        btnSmsContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBlockSmsActivity.this, ListSmsContacts.class);
                intent.putExtra("SELECT_SMS_NUMBER", "Select a number phone");
                startActivityForResult(intent, 4);
            }
        });
        btnSmsReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSmsName.setText("");
                edSmsNumber.setText("");
            }
        });
        btnSmsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddBlockSmsActivity.this);
                builder.setTitle("Notification")
                        .setMessage("Are you sure your choice?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name2 = edSmsName.getText().toString();
                                String number2 = edSmsNumber.getText().toString();
                                addSmsDB(name2, number2);
                                Intent data2 = new Intent(AddBlockSmsActivity.this, MainActivity.class);
                                data2.putExtra("NAME", name2);
                                data2.putExtra("NUMBER", number2);
                                setResult(300, data2);
                                finish();
                            };
                        }).create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 400) {
            String resultName = data.getStringExtra("RETURN_SMS_NAME");
            String resultNumber = data.getStringExtra("RETURN_SMS_NUMBER");

            edSmsName.setText(resultName);
            edSmsNumber.setText(resultNumber);
        }
    }
    public void addSmsDB(String name, String number){
        BlockedDatabase db = new BlockedDatabase(this);
        Contact item =new Contact(name, number, true);
        db.addSms(item);
    }
}

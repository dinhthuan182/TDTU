package com.example.blockingcallsapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.security.Permission;


public class AddBlock extends AppCompatActivity implements View.OnClickListener {

    private EditText edPhoneNumber, edPhoneName;
    private Button btnReset;
    private Button btnSubmit;
    private ImageButton btnContact;
    private static final int RESULT_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_block);

        edPhoneNumber = (EditText) findViewById(R.id.edPhoneNumber);
        edPhoneName = (EditText) findViewById(R.id.edPhoneName);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnContact =(ImageButton) findViewById(R.id.btnContact);

        btnContact.setImageResource(R.drawable.contact);
        btnContact.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddBlock.this);
                builder.setTitle("Notification")
                        .setMessage("Are you sure your choice?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name2 = edPhoneName.getText().toString();
                                String number2 = edPhoneNumber.getText().toString();
                                addContactDB(name2, number2);
                                Intent data2 = new Intent(AddBlock.this, MainActivity.class);
                                data2.putExtra("NAME", name2);
                                data2.putExtra("NUMBER", number2);
                                setResult(RESULT_REQUEST_CODE, data2);
                                finish();
                            };
                        }).create().show();
           }
        });//end btnSubmit
    }//end onCreeate

    @Override
    public void onClick(View v) {
        if(v == btnContact){
            Intent intent = new Intent(AddBlock.this,list_contact.class);
            intent.putExtra("SELECT_NUMBER", "Select a number phone");
            startActivityForResult(intent, 2);
        }
        if(v == btnReset){
            Reset();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 200) {
            String resultName = data.getStringExtra("RETURN_NAME");
            String resultNumber = data.getStringExtra("RETURN_NUMBER");

            edPhoneName.setText(resultName);
            edPhoneNumber.setText(resultNumber);
        }
    }

    private void Reset(){
        edPhoneName.setText("");
        edPhoneNumber.setText("");
    }
    public void addContactDB(String name, String number){
        BlockedDatabase db = new BlockedDatabase(this);
        Contact item =new Contact(name, number, true);
        db.addContact(item);
    }
}//end class AddBlock
